package com.lightshell.transport.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author KevinDong
 */
@EnableWebSecurity
public class ResourceServerConfig {

    // @formatter:off
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable()
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/**/tenant","/**/tenant/**","/**/token")
                                .authenticated()
                                .anyRequest()
                                .permitAll()
                )
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
    // @formatter:on

}
