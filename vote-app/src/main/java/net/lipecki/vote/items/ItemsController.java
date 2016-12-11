package net.lipecki.vote.items;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import net.lipecki.vote.db.tables.pojos.Item;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping({"", "/"})
    public ItemDto addItem(@RequestBody final ItemDto itemDto) {
        log.info("User request to add new item [item={}]", itemDto);
        itemRepository.addItem(new Item(null, itemDto.getTitle(), itemDto.getType(), itemDto.getDetails()));
        return itemDto;
    }

    @GetMapping({"", "/"})
    public List<ItemDto> findAllSummaries() {
        log.debug("User request for all item summaries");
        return itemRepository.findAll()
                .stream()
                .map(
                        itemAggregate -> ItemDto
                                .builder()
                                .id(itemAggregate.getItem().getId())
                                .title(itemAggregate.getItem().getTitle())
                                .type(itemAggregate.getItem().getType())
                                .details(itemAggregate.getItem().getDetails())
                                .build()
                )
                .collect(Collectors.toList());
    }

}
