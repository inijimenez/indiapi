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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IndiapiApplicationTests {

    public static final String REST_URL = "/api/v1/price/{brandID}/{productID}/{date}";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    List<IndiapiTest> testList = new ArrayList<IndiapiTest>();
    List<IndiapiTestResult> testResultsList = new ArrayList<IndiapiTestResult>();


    @Autowired
    private MockMvc mockMvc;

    public IndiapiApplicationTests() {
        // TEST 1
        // petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA
        // expected: price_list = 1 & price= 35.5
        testList.add(new IndiapiTest(1L, 35455L, "2020-06-14T100000"));
        testResultsList.add(new IndiapiTestResult(1L, "2020-06-14T00:00:00", "2020-12-31T23:59:59", 0, 35.5F, "EUR"));

        // TEST 2
        // petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        // expected: price_list = 2 & price= 25.45
        testList.add(new IndiapiTest(1L, 35455L, "2020-06-14T160000"));
        testResultsList.add(new IndiapiTestResult(2L, "2020-06-14T15:00:00", "2020-06-14T18:30:00", 1, 25.45F, "EUR"));

        // TEST 3
        // petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        // expected: price_list = 1 & price= 35.5
        testList.add(new IndiapiTest(1L, 35455L, "2020-06-14T210000"));
        testResultsList.add(new IndiapiTestResult(1L, "2020-06-14T00:00:00", "2020-12-31T23:59:59", 0, 35.5F, "EUR"));

        // TEST 4
        // petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
        // expected: price_list = 3 & price= 30.5
        testList.add(new IndiapiTest(1L, 35455L, "2020-06-15T100000"));
        testResultsList.add(new IndiapiTestResult(3L, "2020-06-15T00:00:00", "2020-06-15T11:00:00", 1, 30.5F, "EUR"));

        // TEST 5
        // petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
        // expected: price_list = 4 & price= 35.5
        testList.add(new IndiapiTest(1L, 35455L, "2020-06-16T210000"));
        testResultsList.add(new IndiapiTestResult(4L, "2020-06-15T16:00:00", "2020-12-31T23:59:59", 1, 38.95F, "EUR"));
    }


    private void doTest(IndiapiTest test, IndiapiTestResult expected) throws Exception {
        // when + then
        this.mockMvc.perform(get(REST_URL, test.getBrand_id(), test.getProduct_id(), test.getDate()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.id").value(test.getBrand_id()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product_id").value(test.getProduct_id()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price_list").value(expected.getPrice_list()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.start_date").value(expected.getStart_date()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.end_date").value(expected.getEnd_date()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priority").value(expected.getPriority()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(expected.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.curr").value(expected.getCurr()));

        logger.info("TEST. {}", test.toString());
    }

    @Test
    public void GetPriceTest1() throws Exception {
        doTest(testList.get(0), testResultsList.get(0));
    }

    @Test
    public void GetPriceTest2() throws Exception {
        doTest(testList.get(1), testResultsList.get(1));
    }

    @Test
    public void GetPriceTest3() throws Exception {
        doTest(testList.get(2), testResultsList.get(2));
    }

    @Test
    public void GetPriceTest4() throws Exception {
        doTest(testList.get(3), testResultsList.get(3));
    }

    @Test
    public void GetPriceTest5() throws Exception {
        doTest(testList.get(4), testResultsList.get(4));
    }
}