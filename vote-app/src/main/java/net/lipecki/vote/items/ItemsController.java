package net.lipecki.vote.items;

import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
@Slf4j
public class ItemsController {

    private final ItemRepository itemRepository;

    public ItemsController(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping({"", "/"})
    public List<ItemSummaryDto> findAllSummaries() {
        log.debug("User request for all item summaries");
        return itemRepository.findItemSummaries()
                .stream()
                .map(
                        itemAggregate -> ItemSummaryDto
                                .builder()
                                .title(itemAggregate.getItem().getTitle())
                                .type(itemAggregate.getItem().getType())
                                .details(itemAggregate.getItem().getDetails())
                                .build()
                )
                .collect(Collectors.toList());
    }

}
