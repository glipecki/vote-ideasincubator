package net.lipecki.vote.items;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.vote.VoteApplicationTests;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
public class CreateItemItTest extends VoteApplicationTests {

    @Test
    public void itemAddedToList() throws Exception {
        // given
        final ItemDto itemToAdd = ItemDto
                .builder()
                .title("title")
                .details("details")
                .type("type")
                .build();

        // when
        performPost(ItemsController.RESOURCE, itemToAdd).andExpect(status().isOk());
        final ItemDto[] result = performGet(ItemsController.RESOURCE, ItemDto[].class);

        // then
        assertThat(result).hasSize(1);
        assertThat(result[0].getTitle()).isEqualTo(itemToAdd.getTitle());
        assertThat(result[0].getType()).isEqualTo(itemToAdd.getType());
        assertThat(result[0].getDetails()).isEqualTo(itemToAdd.getDetails());
    }

}
