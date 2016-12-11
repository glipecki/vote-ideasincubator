package net.lipecki.vote;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@TestPropertySource(value = "classpath:application-test.properties")
@SpringBootTest
@Slf4j
public class VoteApplicationTests {

	public VoteApplicationTests() {
		System.setProperty("org.jooq.no-logo", "true");
	}

	@Test
	public void contextLoads() {
        assertThat(voteApplication).isNotNull();
	}

	@Autowired
	private VoteApplication voteApplication;

}
