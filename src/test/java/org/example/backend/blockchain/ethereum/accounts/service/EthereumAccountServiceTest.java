package org.example.backend.blockchain.ethereum.accounts.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class EthereumAccountServiceTest {

    @InjectMocks
    private EthereumAccountService ethereumAccountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void getEtherBalanceAndTransactionHistory() {
//        // Mocking static methods for EthereumAccountMapper
//        try (MockedStatic<EthereumAccountMapper> mockedStatic = mockStatic(EthereumAccountMapper.class)) {
//            EthereumAccountDto ethereumAccountDto = new EthereumAccountDto();
//            EthereumAccount ethereumAccount = new EthereumAccount();
//
//            // Defining behavior for static methods
//            mockedStatic.when(() -> EthereumAccountMapper.mapAccountDtoToAccount(ethereumAccountDto)).thenReturn(ethereumAccount);
//            mockedStatic.when(() -> EthereumAccountMapper.mapAccountToAccountDto(ethereumAccount)).thenReturn(ethereumAccountDto);
//
//            // Mocking static UriComponentsBuilder
//            try (MockedStatic<UriComponentsBuilder> mockedUriBuilder = mockStatic(UriComponentsBuilder.class)) {
//                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://mocked.url");
//
//                // Ensuring that fromHttpUrl returns a valid builder instance
//                mockedUriBuilder.when(() -> UriComponentsBuilder.fromHttpUrl(eq("http://mocked.url"))).thenReturn(builder);
//
//                // Now set up the queryParam behavior on the builder
//                when(builder.queryParam(anyString(), Optional.ofNullable(any()))).thenReturn(builder); // Mock queryParam to return the builder itself
//
//                // Execute the service method
//                Optional<EthereumAccountDto> result = ethereumAccountService.getEtherBalanceAndTransactionHistory("0x4838B106FCe9647Bdf1E7877BF73cE8B0BAD5f97");
//
//                // Verifying the results
//                assertTrue(result.isPresent());
//                assertEquals(ethereumAccountDto, result.get());
//            }
//        }
//    }
//
//
//    @Test
//    void getTokenBalance() {
//        // Mocking the URL
//        try (MockedStatic<UriComponentsBuilder> mockedStatic = mockStatic(UriComponentsBuilder.class)) {
//            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://mocked.url");
//            mockedStatic.when(() -> UriComponentsBuilder.fromHttpUrl(anyString())).thenReturn(builder);
//
//            // Mocking the service method directly
//            when(ethereumAccountService.getTokenBalance(anyString(), anyString())).thenReturn(100.0);
//
//            double balance = ethereumAccountService.getTokenBalance("testAddress", "testContractAddress");
//
//            assertEquals(100.0, balance);
//        }
//    }
//
//    @Test
//    void getERC20TokenTransfers() {
//        // Mocking the URL
//        try (MockedStatic<UriComponentsBuilder> mockedStatic = mockStatic(UriComponentsBuilder.class)) {
//            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://mocked.url");
//            mockedStatic.when(() -> UriComponentsBuilder.fromHttpUrl(anyString())).thenReturn(builder);
//
//            List<EthereumTransactionDto> transactions = List.of(new EthereumTransactionDto());
//            // Mocking the service method directly
//            when(ethereumAccountService.getERC20TokenTransfers(anyString(), anyInt(), anyInt(), anyString())).thenReturn(Optional.of(transactions));
//
//            Optional<List<EthereumTransactionDto>> result = ethereumAccountService.getERC20TokenTransfers("testAddress", 0, 99999999, "asc");
//
//            assertTrue(result.isPresent());
//            assertEquals(transactions, result.get());
//        }
//    }
//
//    @Test
//    void getERC721TokenTransfers() {
//        // Mocking the URL
//        try (MockedStatic<UriComponentsBuilder> mockedStatic = mockStatic(UriComponentsBuilder.class)) {
//            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://mocked.url");
//            mockedStatic.when(() -> UriComponentsBuilder.fromHttpUrl(anyString())).thenReturn(builder);
//
//            List<EthereumTransactionDto> transactions = List.of(new EthereumTransactionDto());
//            // Mocking the service method directly
//            when(ethereumAccountService.getERC721TokenTransfers(anyString(), anyInt(), anyInt(), anyString())).thenReturn(Optional.of(transactions));
//
//            Optional<List<EthereumTransactionDto>> result = ethereumAccountService.getERC721TokenTransfers("testAddress", 0, 99999999, "asc");
//
//            assertTrue(result.isPresent());
//            assertEquals(transactions, result.get());
//        }
//    }
//
//    @Test
//    void getBlocksMinedByAddress() {
//        // Mocking the URL
//        try (MockedStatic<UriComponentsBuilder> mockedStatic = mockStatic(UriComponentsBuilder.class)) {
//            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://mocked.url");
//            mockedStatic.when(() -> UriComponentsBuilder.fromHttpUrl(anyString())).thenReturn(builder);
//
//            // Mocking the service method directly
//            when(ethereumAccountService.getBlocksMinedByAddress(anyString())).thenReturn("blocksMined");
//
//            String result = ethereumAccountService.getBlocksMinedByAddress("testAddress");
//
//            assertEquals("blocksMined", result);
//        }
//    }
}