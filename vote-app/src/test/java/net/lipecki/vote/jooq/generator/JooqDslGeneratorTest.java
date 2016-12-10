package net.lipecki.vote.jooq.generator;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.vote.VoteApplicationTests;
import org.jooq.util.GenerationTool;
import org.jooq.util.jaxb.Configuration;
import org.jooq.util.jaxb.Database;
import org.jooq.util.jaxb.Generator;
import org.jooq.util.jaxb.Jdbc;
import org.jooq.util.jaxb.Target;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

/**
 * Generates jOOQ DSL classes based on in memory created h2 db (based on liquibase changelog).
 * <p>
 *     Remove @Ignore before run and copy generated resources to src/java folder after.
 * </p>
 */
@Slf4j
@Ignore(value = "Use manually only when DB schema changed")
public class JooqDslGeneratorTest extends VoteApplicationTests {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Test
    public void generateJooqDsl() throws Exception {
        final String packageName = "net.lipecki.vote.db";
        final String targetDir = "target/jooq-generated";

        log.info("Generating JOOQ sources for jdbc url: {}", datasourceUrl);
        final Configuration configuration = new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver("org.h2.Driver")
                        .withUrl(datasourceUrl)
                        .withUser("sa")
                        .withPassword(""))
                .withGenerator(new Generator()
                        .withDatabase(new Database()
                                .withName("org.jooq.util.h2.H2Database")
                                .withIncludes(".*")
                                .withExcludes("")
                                .withInputSchema("PUBLIC"))
                        .withTarget(new Target()
                                .withPackageName(packageName)
                                .withDirectory(targetDir)));
        GenerationTool.generate(configuration);
        log.info("Jooq DSL classes generated in {}, remember to copy them to src/java", targetDir);
    }

}
