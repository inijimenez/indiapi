package com.ijimenez.indiapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndiapiTest {
    private Long brand_id;
    private Long product_id;
    private String date;

    public IndiapiTest(Long brand_id, Long product_id, String date) {
        this.brand_id = brand_id;
        this.product_id = product_id;
        this.date = date;
    }
}
