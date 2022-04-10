package com.nur.readingisgood.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class NewOrderDto {
    private HashMap<String, Integer> items;
    private String customerId;
}
