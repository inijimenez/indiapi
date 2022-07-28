package com.ijimenez.indiapi;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc

class IndiapiApplicationTests {

    public static final String REST_URL = "/api/v1/price/{brandID}/{productID}/{date}";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void GetPriceTest1() throws Exception {
        Long BrandID = 1L;
        Long ProductID = 35455L;
        String Date = "2020-06-14 10.00.00";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(REST_URL, BrandID, ProductID, Date)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        logger.info("TEST 1 - brandID:{} productID: {} date:{} --> {}",BrandID,ProductID, Date, result.getResponse().getContentAsString());
    }

    @Test
    public void GetPriceTest2() throws Exception {
        Long BrandID = 1L;
        Long ProductID = 35455L;
        String Date = "2020-06-14 16.00.00";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(REST_URL, BrandID, ProductID, Date)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        logger.info("TEST 2 - brandID:{} productID: {} date:{} --> {}",BrandID,ProductID, Date, result.getResponse().getContentAsString());
    }
    @Test
    public void GetPriceTest3() throws Exception {
        Long BrandID = 1L;
        Long ProductID = 35455L;
        String Date = "2020-06-14 21.00.00";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(REST_URL, BrandID, ProductID, Date)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        logger.info("TEST 3 - brandID:{} productID: {} date:{} --> {}",BrandID,ProductID, Date, result.getResponse().getContentAsString());
    }

    @Test
    public void GetPriceTest4() throws Exception {
        Long BrandID = 1L;
        Long ProductID = 35455L;
        String Date = "2020-06-15 10.00.00";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(REST_URL, BrandID, ProductID, Date)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        logger.info("TEST 4 - brandID:{} productID: {} date:{} --> {}",BrandID,ProductID, Date, result.getResponse().getContentAsString());
    }
    @Test
    public void GetPriceTest5() throws Exception {
        Long BrandID = 1L;
        Long ProductID = 35455L;
        String Date = "2020-06-16 21.00.00";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(REST_URL, BrandID, ProductID, Date)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        logger.info("TEST 5 - brandID:{} productID: {} date:{} --> {}",BrandID,ProductID, Date, result.getResponse().getContentAsString());
    }
}