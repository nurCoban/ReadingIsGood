package com.nur.readingisgood.dtos;

import com.nur.readingisgood.enums.OrderStatus;
import com.nur.readingisgood.models.OrderLine;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private List<OrderLine> items;
    private String customerId;
    private OrderStatus status;
    private LocalDateTime createdDateTime;
}
