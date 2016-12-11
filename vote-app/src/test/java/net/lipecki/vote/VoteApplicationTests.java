package net.lipecki.vote;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

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

    protected <T> T parseResponse(MockHttpServletResponse response, Class<T> aClass) {
        try {
            return objectMapper.readValue(response.getContentAsString(), aClass);
        } catch (IOException e) {
            throw new TestException("Can't parse JSON response", e);
        }
    }

    @Autowired
    private VoteApplication voteApplication;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

}
