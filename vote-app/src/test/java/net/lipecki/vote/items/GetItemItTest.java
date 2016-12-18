package net.lipecki.vote.items;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.vote.VoteApplicationTests;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class GetItemItTest extends VoteApplicationTests {

    @Test
    public void itemDetailsAvailable() throws Exception {
        system().login();

        // given
        final ItemDto itemToAdd = ItemDto
                .builder()
                .title("title")
                .details("details")
                .type("type")
                .build();
        final ItemDto item = system().addItem(itemToAdd);

        // when
        final ItemDto result = system().getItem(item.getId());

        // then
        assertThat(result.getTitle()).isEqualTo(itemToAdd.getTitle());
        assertThat(result.getType()).isEqualTo(itemToAdd.getType());
        assertThat(result.getDetails()).isEqualTo(itemToAdd.getDetails());
    }

}
