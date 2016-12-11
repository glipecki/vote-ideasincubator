package net.lipecki.vote.items;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import net.lipecki.vote.db.tables.pojos.Item;
import net.lipecki.vote.db.tables.pojos.Tag;

@Data
@Builder
public class ItemSummaryAggregate {

    private Item item;

    private int commentCount;

    private int voteCount;

    private List<Tag> tags;

}
