package com.kvl.library.config;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class LiquibaseInitializer {

    private final ResourceLoader resourceLoader;

    // Automatically injects the active profile name (dev or prod) from properties
    @Value("${spring.profiles.active:dev}")
    private String activeProfile;

    public LiquibaseInitializer(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setResourceLoader(resourceLoader);

        // Point to our universal SQL script
        liquibase.setChangeLog("classpath:/db/changelog/migration.sql");
        liquibase.setDataSource(dataSource);

        // Dynamically matches the "context:dev" string inside your migration.sql
        liquibase.setContexts(activeProfile);

        return liquibase;
    }
}