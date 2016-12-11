package net.lipecki.vote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@TestPropertySource(value = "classpath:application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Slf4j
public class VoteApplicationTests {

    public VoteApplicationTests() {
        System.setProperty("org.jooq.no-logo", "true");
    }

    @Test
    public void contextLoads() {
        assertThat(voteApplication).isNotNull();
    }

    protected MockMvc mockMvc() {
        return mockMvc;
    }

    protected String toJson(final Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new TestException("Can't writa object as JSON", e);
        }
    }

    protected <T> T fromJson(final String json, final Class<T> aClass) {
        try {
            return objectMapper.readValue(json, aClass);
        } catch (IOException e) {
            throw new TestException("Can't parse JSON response", e);
        }
    }

    protected ResultActions performPost(final String resource, final Object object) {
        try {
            return mockMvc().perform(
                    post(resource)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(object))
            );
        } catch (final Exception e) {
            throw new TestException("Can't perform POST request", e);
        }
    }

    protected <T> T performGet(final String resource, final Class<T> aClass) {
        try {
            return fromJson(
                    mockMvc().perform(get(resource))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString(),
                    aClass
            );
        } catch (final Exception e) {
            throw new TestException("Can't perform GET request", e);
        }
    }

    @Autowired
    private VoteApplication voteApplication;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

}
