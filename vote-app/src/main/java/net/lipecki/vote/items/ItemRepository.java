package net.lipecki.vote.items;

import net.lipecki.vote.db.tables.daos.ItemDao;
import net.lipecki.vote.db.tables.pojos.Item;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static net.lipecki.vote.db.tables.Item.ITEM;

@Component
@Transactional
public class ItemRepository {

    private final DSLContext dsl;

    private final ItemDao itemDao;

    public ItemRepository(final DSLContext dsl, final ItemDao itemDao) {
        this.dsl = dsl;
        this.itemDao = itemDao;
    }

    public List<ItemAggregate> findAll() {
        return dsl
                .select()
                .from(ITEM)
                .fetch()
                .into(Item.class)
                .stream()
                .map(
                        item -> ItemAggregate
                                .builder()
                                .item(item)
                                .build()
                )
                .collect(Collectors.toList());
    }

    public void addItem(final Item item) {
        itemDao.insert(item);
    }

}
