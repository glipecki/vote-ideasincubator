package net.lipecki.vote.items;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.vote.VoteApplicationTests;
import net.lipecki.vote.db.tables.daos.ItemDao;
import net.lipecki.vote.db.tables.pojos.Item;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
public class CreatingItemsItTest extends VoteApplicationTests {

    @Autowired
    private ItemDao itemDao;

    @Test
    public void itemAddedToList() throws Exception {
        // given
        itemDao.insert(new Item(null, "title", "type", "details"));

        // when
        final ItemSummaryDto[] result = parseResponse(
                mockMvc()
                        .perform(get("/items"))
                        .andExpect(status().isOk())
                        .andReturn().getResponse(),
                ItemSummaryDto[].class
        );

        // then
        assertThat(result).hasSize(1);
        assertThat(result[0].getTitle()).isEqualTo("title");
        assertThat(result[0].getType()).isEqualTo("type");
        assertThat(result[0].getDetails()).isEqualTo("details");
    }

}
