package org.example.backend.blockchain.ethereum.block.service;

class EthereumBlockServiceTest {

//    @Mock
//    private RestTemplate restTemplate;
//
//    @InjectMocks
//    private EthereumBlockService ethereumBlockService;
//
//    @Value("${etherscan.api.url}")
//    private String apiUrl;
//
//    @Value("${etherscan.api.key}")
//    private String apiKey;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getBlockByNumber_ShouldReturnBlockDto_WhenBlockExists() {
//        // Arrange
//        long blockNumber = 12345;
//        String jsonResponse = "{ \"result\": { \"blockNumber\": \"12345\", \"hash\": \"0xabc123\" } }"; // Fake response
//
//        // Mock the response for the restTemplate.getForObject call
//        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);
//
//        // Mock UriComponentsBuilder to return the correct URL
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl);
//        when(builder.toUriString()).thenReturn(apiUrl);
//
//        // Act
//        Optional<EthereumBlockDto> result = ethereumBlockService.getBlockByNumber(blockNumber);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals("12345", result.get().getNumber());
//    }
//
//    @Test
//    void getMinedBlocks_ShouldReturnBlockDtos_WhenBlocksExist() {
//        // Arrange
//        String address = "0x123456";
//        String blockType = "blocks";
//        String jsonResponse = "{ \"result\": [{\"blockNumber\": \"12345\", \"hash\": \"0xabc123\"}] }"; // Fake response
//        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);
//
//        // Act
//        Optional<List<EthereumBlockDto>> result = ethereumBlockService.getMinedBlocks(address, blockType);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals(1, result.get().size());
//        assertEquals("12345", result.get().get(0).getNumber());
//    }
//
//    @Test
//    void getEthSupply_ShouldReturnSupply() {
//        // Arrange
//        String jsonResponse = "{ \"result\": \"115000000\" }"; // Fake response
//        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);
//
//        // Act
//        Optional<String> result = ethereumBlockService.getEthSupply();
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals("115000000", result.get());
//    }
//
//    @Test
//    void getEthPrice_ShouldReturnEthPrice() {
//        // Arrange
//        String jsonResponse = "{ \"result\": { \"ethusd\": \"4000\" } }"; // Fake response
//        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);
//
//        // Act
//        Optional<String> result = ethereumBlockService.getEthPrice();
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals("4000", result.get());
//    }
//
//    @Test
//    void getBlockCountdown_ShouldReturnCountdown() {
//        // Arrange
//        int blockNumber = 12345;
//        String jsonResponse = "10"; // Fake response (seconds)
//        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);
//
//        // Act
//        String result = ethereumBlockService.getBlockCountdown(blockNumber);
//
//        // Assert
//        assertEquals("10", result);
//    }
//
//    @Test
//    void getBlockByTimestamp_ShouldReturnBlockNumber() {
//        // Arrange
//        long timestamp = 1618312300L; // Fake timestamp
//        String closest = "before";
//        String jsonResponse = "12345"; // Fake block number
//        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);
//
//        // Act
//        String result = ethereumBlockService.getBlockByTimestamp(timestamp, closest);
//
//        // Assert
//        assertEquals("12345", result);
//    }
}
