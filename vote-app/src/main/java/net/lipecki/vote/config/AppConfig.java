package net.lipecki.vote.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties("app")
@Data
public class AppConfig {

    @Data
    static class AppDbConfig {

        @NotNull
        private String path;

    }

    @NotNull
    private AppDbConfig db;

}
