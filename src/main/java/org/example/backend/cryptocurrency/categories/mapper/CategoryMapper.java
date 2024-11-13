package org.example.backend.cryptocurrency.categories.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.cryptocurrency.categories.entity.Category;
import org.example.backend.cryptocurrency.categories.entity.CategoryDto;
import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.Cryptocurrency;

import java.util.ArrayList;
import java.util.List;


public class CategoryMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .title(category.getTitle())
                .description(category.getDescription())
                .numberOfTokens(category.getNumberOfTokens())
                .avgPriceChange(category.getAvgPriceChange())
                .marketCap(category.getMarketCap())
                .marketCapChange(category.getMarketCapChange())
                .volume(category.getVolume())
                .volumeChange(category.getVolumeChange())
//                .cryptocurrencies(
//                        category.getCryptocurrencies().stream()
//                                .map(cryptocurrency -> CryptocurrencyMapper.mapCryptocurrencyToCryptocurrencyDto(cryptocurrency))// Assuming Cryptocurrency has a toDto method
//                                .collect(Collectors.toList())
//                )
                .build();
    }

    public static Category toEntity(CategoryDto categoryDto) {
        return Category.builder()
                .categoryId(categoryDto.getCategoryId())
                .name(categoryDto.getName())
                .title(categoryDto.getTitle())
                .description(categoryDto.getDescription())
                .numberOfTokens(categoryDto.getNumberOfTokens())
                .avgPriceChange(categoryDto.getAvgPriceChange())
                .marketCap(categoryDto.getMarketCap())
                .marketCapChange(categoryDto.getMarketCapChange())
                .volume(categoryDto.getVolume())
                .volumeChange(categoryDto.getVolumeChange())
//                .cryptocurrencies(
//                        categoryDto.getCryptocurrencies().stream()
//                                .map(cryptocurrency -> CryptocurrencyMapper.mapCryptocurrencyDtoToCryptocurrency(cryptocurrency))// Assuming Cryptocurrency has a toDto method
//                                .collect(Collectors.toList())
//                )
                .build();
    }

    public static List<Category> mapJsonResponseToCategories(String jsonResponse) {
        List<Category> categories = new ArrayList<>();

        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = rootNode.path("data");

            if (dataNode.isArray()) {
                for (JsonNode categoryNode : dataNode) {
                    Category category = Category.builder()
                            .categoryId(categoryNode.path("id").asText())
                            .name(categoryNode.path("name").asText())
                            .title(categoryNode.path("title").asText())
                            .description(categoryNode.path("description").asText())
                            .numberOfTokens(categoryNode.path("num_tokens").asLong())
                            .avgPriceChange(categoryNode.path("avg_price_change").asDouble())
                            .marketCap(String.valueOf(categoryNode.path("market_cap").asDouble()))
                            .marketCapChange(String.valueOf(categoryNode.path("market_cap_change").asDouble()))
                            .volume(String.valueOf(categoryNode.path("volume").asDouble()))
                            .volumeChange(String.valueOf(categoryNode.path("volume_change").asDouble()))
                            .cryptocurrencies(new ArrayList<>()) // Initialize an empty list
                            .build();

                    // Add the category to the list
                    categories.add(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }


    public static Category mapJsonResponseToCategoryWithCryptocurrencies(String jsonResponse) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = rootNode.path("data");

            if (dataNode.isMissingNode()) {
                return null;
            }

            Category category = new Category();
            category.setCategoryId(dataNode.path("id").asText());
            category.setName(dataNode.path("name").asText());
            category.setTitle(dataNode.path("title").asText());
            category.setDescription(dataNode.path("description").asText());
            category.setNumberOfTokens(dataNode.path("num_tokens").asLong());
            category.setAvgPriceChange(dataNode.path("avg_price_change").asDouble());
            category.setMarketCap(dataNode.path("market_cap").asText());
            category.setMarketCapChange(dataNode.path("market_cap_change").asText());
            category.setVolume(dataNode.path("volume").asText());
            category.setVolumeChange(dataNode.path("volume_change").asText());

            List<Cryptocurrency> cryptocurrencies = new ArrayList<>();
            JsonNode coinsNode = dataNode.path("coins");
            for (JsonNode coinNode : coinsNode) {
                Cryptocurrency crypto = new Cryptocurrency();
                crypto.setCmcId(coinNode.path("id").asLong());
                crypto.setName(coinNode.path("name").asText());
                crypto.setSymbol(coinNode.path("symbol").asText());
                crypto.setCmcRank(coinNode.path("cmc_rank").asLong());
                // Add other fields if needed
                cryptocurrencies.add(crypto);
            }
            category.setCryptocurrencies(cryptocurrencies);

            return category;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
