package net.lipecki.vote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Slf4j
public class VoteApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        System.setProperty("org.jooq.no-logo", "true");
        SpringApplication.run(VoteApplication.class, args);
    }

}
