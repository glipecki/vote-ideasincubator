package net.lipecki.vote;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.vote.db.tables.Votes;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @author gregorry
 */
@RestController
@Slf4j
public class GreetingController {

    private final DSLContext create;

    public GreetingController(final DSLContext create) {
        this.create = create;
    }

    @GetMapping("/greeting")
    public String greetingGet() {
        final Result<Record> votes = create.select().from(Votes.VOTES).fetch();
        log.debug("Votes:\n{}", votes);
        return "Welcome!";
    }


    @PostConstruct
    public void putSomData() {
        create.insertInto(Votes.VOTES, Votes.VOTES.TITLE, Votes.VOTES.TYPE, Votes.VOTES.DETAILS)
                .values("Sample title " + System.currentTimeMillis(), "proposal", "details")
                .execute();
    }

}
