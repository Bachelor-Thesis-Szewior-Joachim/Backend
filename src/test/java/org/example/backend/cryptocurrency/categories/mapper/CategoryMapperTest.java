package org.example.backend.cryptocurrency.categories.mapper;

import org.example.backend.cryptocurrency.categories.entity.Category;
import org.example.backend.cryptocurrency.categories.entity.CategoryDto;
import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.Cryptocurrency;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoryMapperTest {

    @Test
    public void testToDto() {
        Category category = Category.builder()
                .categoryId("1")
                .name("name")
                .title("title")
                .description("description")
                .numberOfTokens(10L)
                .avgPriceChange(5.0)
                .marketCap("1000")
                .marketCapChange("10")
                .volume("500")
                .volumeChange("5")
                .build();

        CategoryDto dto = CategoryMapper.toDto(category);

        assertNotNull(dto);
        assertEquals(category.getCategoryId(), dto.getCategoryId());
        assertEquals(category.getName(), dto.getName());
        assertEquals(category.getTitle(), dto.getTitle());
        assertEquals(category.getDescription(), dto.getDescription());
        assertEquals(category.getNumberOfTokens(), dto.getNumberOfTokens());
        assertEquals(category.getAvgPriceChange(), dto.getAvgPriceChange());
        assertEquals(category.getMarketCap(), dto.getMarketCap());
        assertEquals(category.getMarketCapChange(), dto.getMarketCapChange());
        assertEquals(category.getVolume(), dto.getVolume());
        assertEquals(category.getVolumeChange(), dto.getVolumeChange());
    }

    @Test
    public void testToEntity() {
        CategoryDto dto = CategoryDto.builder()
                .categoryId("1")
                .name("name")
                .title("title")
                .description("description")
                .numberOfTokens(10L)
                .avgPriceChange(5.0)
                .marketCap("1000")
                .marketCapChange("10")
                .volume("500")
                .volumeChange("5")
                .build();

        Category category = CategoryMapper.toEntity(dto);

        assertNotNull(category);
        assertEquals(dto.getCategoryId(), category.getCategoryId());
        assertEquals(dto.getName(), category.getName());
        assertEquals(dto.getTitle(), category.getTitle());
        assertEquals(dto.getDescription(), category.getDescription());
        assertEquals(dto.getNumberOfTokens(), category.getNumberOfTokens());
        assertEquals(dto.getAvgPriceChange(), category.getAvgPriceChange());
        assertEquals(dto.getMarketCap(), category.getMarketCap());
        assertEquals(dto.getMarketCapChange(), category.getMarketCapChange());
        assertEquals(dto.getVolume(), category.getVolume());
        assertEquals(dto.getVolumeChange(), category.getVolumeChange());
    }

    @Test
    public void testMapJsonResponseToCategories() throws Exception {
        String jsonResponse = "{ \"data\": [ { \"id\": \"1\", \"name\": \"name\", \"title\": \"title\", \"description\": \"description\", \"num_tokens\": 10, \"avg_price_change\": 5.0, \"market_cap\": 1000.0, \"market_cap_change\": 10.0, \"volume\": 500.0, \"volume_change\": 5.0 } ] }";

        List<Category> categories = CategoryMapper.mapJsonResponseToCategories(jsonResponse);

        assertNotNull(categories);
        assertEquals(1, categories.size());

        Category category = categories.get(0);
        assertEquals("1", category.getCategoryId());
        assertEquals("name", category.getName());
        assertEquals("title", category.getTitle());
        assertEquals("description", category.getDescription());
        assertEquals(10L, category.getNumberOfTokens());
        assertEquals(5.0, category.getAvgPriceChange());
        assertEquals("1000.0", category.getMarketCap());
        assertEquals("10.0", category.getMarketCapChange());
        assertEquals("500.0", category.getVolume());
        assertEquals("5.0", category.getVolumeChange());
    }

    @Test
    public void testMapJsonResponseToCategoryWithCryptocurrencies() throws Exception {
        String jsonResponse = "{ \"data\": { \"id\": \"1\", \"name\": \"name\", \"title\": \"title\", \"description\": \"description\", \"num_tokens\": 10, \"avg_price_change\": 5.0, \"market_cap\": \"1000\", \"market_cap_change\": \"10\", \"volume\": \"500\", \"volume_change\": \"5\", \"coins\": [ { \"id\": 1, \"name\": \"Bitcoin\", \"symbol\": \"BTC\", \"cmc_rank\": 1 } ] } }";

        Category category = CategoryMapper.mapJsonResponseToCategoryWithCryptocurrencies(jsonResponse);

        assertNotNull(category);
        assertEquals("1", category.getCategoryId());
        assertEquals("name", category.getName());
        assertEquals("title", category.getTitle());
        assertEquals("description", category.getDescription());
        assertEquals(10L, category.getNumberOfTokens());
        assertEquals(5.0, category.getAvgPriceChange());
        assertEquals("1000", category.getMarketCap());
        assertEquals("10", category.getMarketCapChange());
        assertEquals("500", category.getVolume());
        assertEquals("5", category.getVolumeChange());

        List<Cryptocurrency> cryptocurrencies = category.getCryptocurrencies();
        assertNotNull(cryptocurrencies);
        assertEquals(1, cryptocurrencies.size());

        Cryptocurrency crypto = cryptocurrencies.get(0);
        assertEquals(1L, crypto.getCmcId());
        assertEquals("Bitcoin", crypto.getName());
        assertEquals("BTC", crypto.getSymbol());
        assertEquals(1L, crypto.getCmcRank());
    }
}