package org.example.backend.resources.news;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class NewsService {

    private final RestTemplate restTemplate;
    @Value("${newsapi.apiKey}")
    private String apiKey;

    @Value("${newsapi.url}")
    private String apiUrl;

    public NewsService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Article> getBlockchainNews() {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q", "blockchain")
                .queryParam("language", "en")
                .queryParam("pageSize", 10)
                .queryParam("apiKey", apiKey)   // API key
                .toUriString();

        NewsApiResponse response = restTemplate.getForObject(url, NewsApiResponse.class);
        return response != null ? response.getArticles() : List.of();
    }
}
