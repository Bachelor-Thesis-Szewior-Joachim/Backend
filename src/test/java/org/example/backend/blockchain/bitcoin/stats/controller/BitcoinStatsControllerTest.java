package org.example.backend.blockchain.bitcoin.stats.controller;

//@WebMvcTest(BitcoinStatsController.class)
//@AutoConfigureMockMvc(addFilters = false) // Disables security filters for this test
//class BitcoinStatsControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BitcoinStatsService bitcoinStatsService;
//
//    @MockBean
//    private JwtTokenUtils jwtUtil; // Mocking JwtUtil to avoid dependency issues
//
//    @Test
//    void testGetStats_ReturnsBitcoinStats() throws Exception {
//        // Arrange
//        String expectedStats = "Bitcoin network is operational with 1000 blocks and 5000 transactions.";
//        when(bitcoinStatsService.getBitcoinMainStatus()).thenReturn(expectedStats);
//
//        // Act and Assert
//        mockMvc.perform(get("/bitcoin/stats")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(expectedStats));
//    }
//}
