package com.personal.beans.config;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.personal.beans.config.ConfigConstants.KEYCLOAK_SPRING_BOOT_CONFIGURATION_RESOLVER_CREATED;

@Configuration
@Slf4j
public class KeycloakConfig {
    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver = new KeycloakSpringBootConfigResolver();
        log.info(KEYCLOAK_SPRING_BOOT_CONFIGURATION_RESOLVER_CREATED);
        return keycloakSpringBootConfigResolver;
    }
}
