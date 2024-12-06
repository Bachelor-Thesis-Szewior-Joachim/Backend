package org.example.backend.client.client.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.example.backend.config.JwtAccessTokenFilter;
import org.example.backend.config.JwtTokenUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenUtils jwtUtil;
    private final UserInfoManager userInfoManager;
    private final RSAKeyRecord rsaKeyRecord;


    public SecurityConfig(JwtTokenUtils jwtUtil, UserInfoManager userInfoManager, RSAKeyRecord rsaKeyRecord) {
        this.jwtUtil = jwtUtil;
        this.userInfoManager = userInfoManager;
        this.rsaKeyRecord = rsaKeyRecord;
    }
    @Order(1)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http
               .securityMatcher(new AntPathRequestMatcher("/client/register"))
               .cors(withDefaults())
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(auth->auth
                       .anyRequest().permitAll()
               )
               .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .build();
    }

    @Order(2)
    @Bean
    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(new AntPathRequestMatcher("/client/login"))
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }


    @Order(3)
    @Bean
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(new OrRequestMatcher(new AntPathRequestMatcher("/blockchain/**"),
                        new AntPathRequestMatcher("/bitcoin/**"),
                        new AntPathRequestMatcher("/ethereum/**"),
                        new AntPathRequestMatcher("/solana/**"),
                        new AntPathRequestMatcher("/categories/**"),
                        new AntPathRequestMatcher("/cryptocurrency/**"),
                        new AntPathRequestMatcher("/global-data/**"),
                        new AntPathRequestMatcher("/collections/**"),
                        new AntPathRequestMatcher("/nft-statistics/**"),
                        new AntPathRequestMatcher("/converter/**"),
                        new AntPathRequestMatcher("/news/**"),
                        new AntPathRequestMatcher("/predictPrices/**")))
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .anyRequest().authenticated()
                )
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtAccessTokenFilter(rsaKeyRecord, jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .oauth2ResourceServer(oauth2->oauth2.jwt(withDefaults()))
                .build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeyRecord.publicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeyRecord.publicKey()).privateKey(rsaKeyRecord.privateKey()).build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }
}
