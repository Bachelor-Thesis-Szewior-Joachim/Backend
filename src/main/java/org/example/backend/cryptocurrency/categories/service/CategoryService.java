package org.example.backend.cryptocurrency.categories.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.backend.cryptocurrency.categories.entity.Category;
import org.example.backend.cryptocurrency.categories.entity.CategoryDto;
import org.example.backend.cryptocurrency.categories.mapper.CategoryMapper;
import org.example.backend.cryptocurrency.categories.repository.CategoryRepository;
import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.Cryptocurrency;
import org.example.backend.cryptocurrency.cryptocurrency.mapper.CryptocurrencyMapper;
import org.example.backend.cryptocurrency.cryptocurrency.repository.CryptocurrencyRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String API_KEY = "d42f0690-3288-4f73-8230-da9ac5135859";
    private static final String API_URL = "https://pro-api.coinmarketcap.com";
    private final CryptocurrencyRepository cryptocurrencyRepository;

    public CategoryService(CategoryRepository categoryRepository, RestTemplate restTemplate, CryptocurrencyRepository cryptocurrencyRepository) {
        this.categoryRepository = categoryRepository;
        this.restTemplate = restTemplate;
        this.cryptocurrencyRepository = cryptocurrencyRepository;
    }

    public Optional<CategoryDto> findByCategoryId(String categoryId) {
        Optional<Category> category = Optional.ofNullable(categoryRepository.findByCategoryId(categoryId));

        if (category.isPresent()) {
            return Optional.of(CategoryMapper.toDto(category.get()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<List<CategoryDto>> findAllCategories() {
        Iterable<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            categoryDtos.add(CategoryMapper.toDto(category));
        }

        if (!categoryDtos.isEmpty()) {
            return Optional.of(categoryDtos);
        } else {
            return Optional.empty();
        }
    }

    public void updateCategoriesWithCryptocurrencies() {
        List<Category> allCategories = (List<Category>) categoryRepository.findAll();
        List<Category> categories = allCategories.subList(0,20);

        for (Category category : categories) {
            try {
                UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(API_URL+ "/v1/cryptocurrency/category")
                        .queryParam("CMC_PRO_API_KEY", API_KEY)
                        .queryParam("id", category.getCategoryId());

                String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

                Category updatedCategory = CategoryMapper.mapJsonResponseToCategoryWithCryptocurrencies(response);
                if (updatedCategory != null) {
                    category.setCryptocurrencies(updatedCategory.getCryptocurrencies());
                    categoryRepository.save(category);

                    for (Cryptocurrency crypto : updatedCategory.getCryptocurrencies()) {
                        if (!cryptocurrencyRepository.existsByCmcId(crypto.getCmcId())) {
                            cryptocurrencyRepository.save(crypto);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
