package net.lipecki.vote.items;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.vote.VoteApplicationTests;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class GetItemItTest extends VoteApplicationTests {

    @Test
    public void itemDetailsAvailable() throws Exception {
        // given
        final ItemDto itemToAdd = ItemDto
                .builder()
                .title("title")
                .details("details")
                .type("type")
                .build();

        // when
        final ItemDto createdItem = fromJson(
                performPost(ItemsController.RESOURCE, itemToAdd)
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(),
                ItemDto.class
        );
        final ItemDto result = performGet(ItemsController.RESOURCE + "/" + createdItem.getId(), ItemDto.class);

        // then
        assertThat(result.getTitle()).isEqualTo(itemToAdd.getTitle());
        assertThat(result.getType()).isEqualTo(itemToAdd.getType());
        assertThat(result.getDetails()).isEqualTo(itemToAdd.getDetails());
    }

}
