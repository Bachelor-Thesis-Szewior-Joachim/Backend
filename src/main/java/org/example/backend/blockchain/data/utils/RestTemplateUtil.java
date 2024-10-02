package org.example.backend.blockchain.data.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateUtil {

    @Bean(name = "restTemplate")
    public RestTemplate customRestTemplate() {
        return new RestTemplate();
    }
}
