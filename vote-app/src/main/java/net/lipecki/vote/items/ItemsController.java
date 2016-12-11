package net.lipecki.vote.items;

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
    public List<String> findAllSummaries() {
        return itemRepository.findAllSummaries();
    }

}
