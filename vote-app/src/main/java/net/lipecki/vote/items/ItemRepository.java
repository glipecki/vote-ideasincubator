package net.lipecki.vote.items;

import net.lipecki.vote.db.tables.daos.VoteDao;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemRepository {

    private final DSLContext dsl;

    private final VoteDao voteDao;

    public ItemRepository(final DSLContext dsl, final VoteDao voteDao) {
        this.voteDao = voteDao;
        this.dsl = dsl;
    }

    public List<String> findAllSummaries() {
        return null;
    }

}
