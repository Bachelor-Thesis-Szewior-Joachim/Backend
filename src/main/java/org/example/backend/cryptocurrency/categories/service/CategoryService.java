package org.example.backend.cryptocurrency.categories.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.cryptocurrency.categories.entity.Category;
import org.example.backend.cryptocurrency.categories.entity.CategoryDto;
import org.example.backend.cryptocurrency.categories.mapper.CategoryMapper;
import org.example.backend.cryptocurrency.categories.repository.CategoryRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String API_KEY = "d42f0690-3288-4f73-8230-da9ac5135859";
    private static final String API_URL = "https://pro-api.coinmarketcap.com";

    public CategoryService(CategoryRepository categoryRepository, RestTemplate restTemplate) {
        this.categoryRepository = categoryRepository;
        this.restTemplate = restTemplate;
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

    @Scheduled(fixedRate = 3600000) // Runs every 1 hour
    public void fetchAndSaveCategories() {
        try {
            String response = restTemplate.getForObject(API_URL + "/v1/cryptocurrency/categories?CMC_PRO_API_KEY=" + API_KEY, String.class);

            List<Category> categories = CategoryMapper.mapJsonResponseToCategory(response);

            categories.forEach(this::updateCategoryWithAdditionalDetails);

            categoryRepository.deleteAll();
            categoryRepository.saveAll(categories);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCategoryWithAdditionalDetails(Category category) {
        try {
            String url = API_URL + "/v1/cryptocurrency/category?id=" + category.getCategoryId() + "&CMC_PRO_API_KEY=" + API_KEY;
            String response = restTemplate.getForObject(url, String.class);

            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode dataNode = rootNode.path("data");

            if (!dataNode.isMissingNode()) {
                category.setMarketCap(dataNode.path("market_cap").asText());
                category.setMarketCapChange(dataNode.path("market_cap_change").asText());
                category.setVolume(dataNode.path("volume").asText());
                category.setVolumeChange(dataNode.path("volume_change").asText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
