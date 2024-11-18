package org.example.backend.blockchain.solana.network.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(NetworkController.class)
public class NetworkControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private NetworkService networkService;
//
//    private SupplyDto supplyDto;
//    private EpochDto epochDto;
//
//    @BeforeEach
//    public void setUp() {
//        supplyDto = new SupplyDto();
//        epochDto = new EpochDto();
//    }
//
//    @Test
//    public void testGetSupply() throws Exception {
//        when(networkService.getSupply()).thenReturn(Optional.of(supplyDto));
//
//        mockMvc.perform(get("/solana/network/supply"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void testGetCurrentEpochInfo() throws Exception {
//        when(networkService.getCurrentEpochInfo()).thenReturn(Optional.of(epochDto));
//
//        mockMvc.perform(get("/solana/network/currentEpoch"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void testGetGenesisHash() throws Exception {
//        String genesisHash = "someGenesisHash";
//        when(networkService.getGenesisHash()).thenReturn(Optional.of(genesisHash));
//
//        mockMvc.perform(get("/solana/network/genesisHash"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(genesisHash));
//    }
//
//    @Test
//    public void testGetFirstAvailableBlock() throws Exception {
//        String firstAvailableBlock = "someFirstAvailableBlock";
//        when(networkService.getFirstAvailableBlock()).thenReturn(Optional.of(firstAvailableBlock));
//
//        mockMvc.perform(get("/solana/network/firstAvailableBlock"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(firstAvailableBlock));
//    }
//
//    @Test
//    public void testGetMinimumBalanceForRentExemption() throws Exception {
//        String minimumBalance = "someMinimumBalance";
//        Long size = 100L;
//        when(networkService.getMinimumBalanceForRentExemption(size)).thenReturn(Optional.of(minimumBalance));
//
//        mockMvc.perform(get("/solana/network/minimumBalanceForRentExemption").param("size", size.toString()))
//                .andExpect(status().isOk())
//                .andExpect(content().string(minimumBalance));
//    }
}