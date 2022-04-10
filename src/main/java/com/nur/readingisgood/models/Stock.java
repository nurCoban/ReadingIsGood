package com.nur.readingisgood.models;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("stock")
public class Stock {
    @Id
    private String id;
    private String itemId;
    private Integer count;
}
