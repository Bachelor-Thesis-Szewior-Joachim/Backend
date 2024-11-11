package org.example.backend.blockchain.bitcoin.stats.controller;

import org.example.backend.blockchain.bitcoin.stats.controller.BitcoinStatsController;
import org.example.backend.blockchain.bitcoin.stats.service.BitcoinStatsService;
import org.example.backend.config.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BitcoinStatsController.class)
@AutoConfigureMockMvc(addFilters = false) // Disables security filters for this test
class BitcoinStatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BitcoinStatsService bitcoinStatsService;

    @MockBean
    private JwtUtil jwtUtil; // Mocking JwtUtil to avoid dependency issues

    @Test
    void testGetStats_ReturnsBitcoinStats() throws Exception {
        // Arrange
        String expectedStats = "Bitcoin network is operational with 1000 blocks and 5000 transactions.";
        when(bitcoinStatsService.getBitcoinMainStatus()).thenReturn(expectedStats);

        // Act and Assert
        mockMvc.perform(get("/bitcoin/stats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedStats));
    }
}
