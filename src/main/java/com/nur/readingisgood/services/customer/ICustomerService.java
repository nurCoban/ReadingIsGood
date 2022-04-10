package com.nur.readingisgood.services.customer;

import com.nur.readingisgood.exceptions.InvalidRequestException;
import com.nur.readingisgood.models.Customer;
import com.nur.readingisgood.models.Order;

import java.util.List;

public interface ICustomerService {
    void register(Customer customer) throws InvalidRequestException;
    List<Order> getOrders(String customerId);
}
