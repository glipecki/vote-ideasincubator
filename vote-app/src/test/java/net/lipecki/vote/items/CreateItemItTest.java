package net.lipecki.vote.items;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.vote.VoteApplicationTests;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CreateItemItTest extends VoteApplicationTests {

    @Test
    public void itemAddedToList() throws Exception {
        system().login();

        // given
        final ItemDto itemToAdd = ItemDto
                .builder()
                .title("title")
                .details("details")
                .type("type")
                .build();

        // when
        system().addItem(itemToAdd);

        // then
        final ItemDto[] result = system().getItems();
        assertThat(result).hasSize(1);
        assertThat(result[0].getTitle()).isEqualTo(itemToAdd.getTitle());
        assertThat(result[0].getType()).isEqualTo(itemToAdd.getType());
        assertThat(result[0].getDetails()).isEqualTo(itemToAdd.getDetails());
    }

}
