package net.lipecki.vote.items;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import net.lipecki.vote.db.tables.pojos.Comment;
import net.lipecki.vote.db.tables.pojos.Item;
import net.lipecki.vote.db.tables.pojos.Tag;
import net.lipecki.vote.db.tables.pojos.Vote;

@Data
@Builder
public class ItemAggregate {

    private Item item;

    private List<Tag> tags;

    private List<Vote> votes;

    private List<Comment> comments;

}
