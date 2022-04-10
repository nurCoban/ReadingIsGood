package com.nur.readingisgood.models;

import com.nur.readingisgood.enums.OrderStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("order")
public class Order {
    @Id
    private String id;
    private Map<String, OrderLine> lines;

    private String customerId;
    private OrderStatus status;
    private LocalDateTime createdDateTime;

    public Order(String customerId) {
        setStatus(OrderStatus.Processing);
        setCreatedDateTime(LocalDateTime.now());
        setCustomerId(customerId);
        setLines(new HashMap<>());
    }
}
