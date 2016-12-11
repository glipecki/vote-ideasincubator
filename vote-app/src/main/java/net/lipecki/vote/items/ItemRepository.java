package net.lipecki.vote.items;

import java.util.List;
import java.util.stream.Collectors;
import net.lipecki.vote.db.tables.daos.VoteDao;
import net.lipecki.vote.db.tables.pojos.Item;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static net.lipecki.vote.db.tables.Item.ITEM;

@Component
@Transactional
public class ItemRepository {

    private final DSLContext dsl;

    private final VoteDao voteDao;

    public ItemRepository(final DSLContext dsl, final VoteDao voteDao) {
        this.voteDao = voteDao;
        this.dsl = dsl;
    }

    public List<ItemSummaryAggregate> findItemSummaries() {
        final List<Item> items = dsl.select()
                .from(ITEM)
                .fetch()
                .into(Item.class);
        return items.stream()
                .map(
                        item -> ItemSummaryAggregate
                                .builder()
                                .item(item)
                                .build()
                )
                .collect(Collectors.toList());
    }

    public List<ItemAggregate> findItem() {
        return null;
    }

}
