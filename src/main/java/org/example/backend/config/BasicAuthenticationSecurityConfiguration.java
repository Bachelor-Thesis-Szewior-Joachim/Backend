package org.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BasicAuthenticationSecurityConfiguration {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests(
//                authorizeRequests -> authorizeRequests.anyRequest().authenticated()
//        );
//
//        http.httpBasic(Customizer.withDefaults());
//
//        http.sessionManagement(
//                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        );
//
//        http.csrf().disable();
//
//        return http.build();
//    }
}
