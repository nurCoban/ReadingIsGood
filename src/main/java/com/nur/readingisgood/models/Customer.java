package com.nur.readingisgood.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("customer")
public class Customer {
    @Id
    private String id;
    private String email;
}
