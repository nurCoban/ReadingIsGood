package com.nur.readingisgood.models;

import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    @Id
    private String id;
    private String name;
    private Double price;
}
