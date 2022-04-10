package com.nur.readingisgood.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyOrderStatistic {
    private String month;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private Double totalPurchasedAmount;
}
