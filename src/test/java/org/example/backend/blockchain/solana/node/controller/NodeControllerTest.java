package org.example.backend.blockchain.solana.node.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(NodeController.class)
public class NodeControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private NodeService nodeService;
//
//    private List<SolanaClusterNodeDto> clusterNodes;
//
//    @BeforeEach
//    public void setUp() {
//        SolanaClusterNodeDto node1 = new SolanaClusterNodeDto();
//        SolanaClusterNodeDto node2 = new SolanaClusterNodeDto();
//        clusterNodes = List.of(node1, node2);
//    }
//
//    @Test
//    public void testGetClusterNodes() throws Exception {
//        when(nodeService.getClusterNodes()).thenReturn(Optional.of(clusterNodes));
//
//        mockMvc.perform(get("/solana/node/getClusterNodes"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
}