package com.personal.beans.config;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import static com.personal.beans.config.ConfigConstants.SECURITY_CONFIGURATION_ALLOW_DOUBLE_SLASH_IN_STRICT_HTTP_FIREWALL;
import static com.personal.beans.config.ConfigConstants.SECURITY_CONFIGURATION_REGISTER_SESSION_AUTHENTICATION_STRATEGY_CREATED;

@KeycloakConfiguration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@Slf4j
class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy = new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
        log.info(SECURITY_CONFIGURATION_REGISTER_SESSION_AUTHENTICATION_STRATEGY_CREATED);
        return registerSessionAuthenticationStrategy;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .permitAll();
    }

    @Bean
    public HttpFirewall getHttpFirewall() {
        StrictHttpFirewall strictHttpFirewall = new StrictHttpFirewall();
        strictHttpFirewall.setAllowUrlEncodedDoubleSlash(true);
        log.info(SECURITY_CONFIGURATION_ALLOW_DOUBLE_SLASH_IN_STRICT_HTTP_FIREWALL);
        return strictHttpFirewall;
    }
}
