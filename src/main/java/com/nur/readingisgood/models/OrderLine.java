package com.nur.readingisgood.models;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLine {
    private Item item;
    private Integer count;
}
