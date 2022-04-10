package com.nur.readingisgood.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticDto {
    private String month;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private Double totalPurchasedAmount;
}
