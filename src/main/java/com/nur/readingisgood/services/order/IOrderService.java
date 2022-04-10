package com.nur.readingisgood.services.order;

import com.nur.readingisgood.exceptions.EntityNotFoundException;
import com.nur.readingisgood.exceptions.InvalidRequestException;
import com.nur.readingisgood.exceptions.OutOfStockException;
import com.nur.readingisgood.models.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IOrderService {
    void create(Map<String, Integer> items, String customerId) throws OutOfStockException, EntityNotFoundException, InvalidRequestException;
    Order getBy(String id) throws EntityNotFoundException, InvalidRequestException;
    List<Order> listBy(LocalDateTime startDate, LocalDateTime endDate) throws InvalidRequestException;
}
