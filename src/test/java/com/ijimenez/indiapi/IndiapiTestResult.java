package com.ijimenez.indiapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndiapiTestResult {
    Long price_list;
    String start_date;
    String end_date;
    Integer priority;
    Float price;
    String curr;

    public IndiapiTestResult(Long price_list, String start_date, String end_date, Integer priority, Float price, String curr) {
        this.price_list = price_list;
        this.start_date = start_date;
        this.end_date = end_date;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }
}
