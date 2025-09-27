package br.atitus.currencyservice.config;

import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayConfigurationCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig implements FlywayConfigurationCustomizer {

    @Override
    public void customize(FluentConfiguration configuration) {
        System.out.println("Applying Flyway community database support override");
        configuration.communityDBSupportEnabled(true);
    }
}
