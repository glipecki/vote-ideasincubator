package net.lipecki.vote.items;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import net.lipecki.vote.db.tables.pojos.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ItemsController.RESOURCE)
@Slf4j
public class ItemsController {

    public static final String RESOURCE = "/items";

    private final ItemRepository itemRepository;

    public ItemsController(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping({"/{id}"})
    public ItemDto getItem(@PathVariable final String id) {
        return convert(itemRepository.findById(id));
    }

    @GetMapping({"", "/"})
    public List<ItemDto> findAllSummaries() {
        log.debug("User request for all item summaries");
        return itemRepository.findAll()
                .stream()
                .map(itemAggregate -> convert(itemAggregate))
                .collect(Collectors.toList());
    }

    @PostMapping({"", "/"})
    public ItemDto addItem(@RequestBody final ItemDto itemDto) {
        log.info("User request to add new item [item={}]", itemDto);
        final Item item = itemRepository.addItem(new Item(null, itemDto.getTitle(), itemDto.getType(), itemDto.getDetails()));
        return ItemDto.builder()
                .id(item.getId())
                .type(item.getType())
                .title(item.getTitle())
                .details(item.getDetails())
                .build();
    }

    private ItemDto convert(final ItemAggregate itemAggregate) {
        return ItemDto
                .builder()
                .id(itemAggregate.getItem().getId())
                .title(itemAggregate.getItem().getTitle())
                .type(itemAggregate.getItem().getType())
                .details(itemAggregate.getItem().getDetails())
                .build();
    }

}
