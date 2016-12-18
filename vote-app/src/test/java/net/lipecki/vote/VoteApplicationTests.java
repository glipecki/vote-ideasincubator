package net.lipecki.vote;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Before
    public void setUpSystemFacade() {
        systemTestFacade = new SystemTestFacade(mockMvc, objectMapper);
    }


    protected MockMvc mockMvc() {
        return mockMvc;
    }

    protected SystemTestFacade system() {
        return systemTestFacade;
    }

    @Autowired
    private VoteApplication voteApplication;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private SystemTestFacade systemTestFacade;

}
