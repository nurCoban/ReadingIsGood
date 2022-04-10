package com.nur.readingisgood.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("book")
public class Book extends Item {
}
