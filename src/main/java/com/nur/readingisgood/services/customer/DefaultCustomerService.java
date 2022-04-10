package com.nur.readingisgood.services.customer;

import com.nur.readingisgood.models.Customer;
import com.nur.readingisgood.models.Order;
import com.nur.readingisgood.repositories.CustomerRepository;
import com.nur.readingisgood.repositories.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class DefaultCustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public DefaultCustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public void register(Customer customer){
        this.customerRepository.insert(customer);
    }

    public List<Order> getOrders(String customerId){
        List<Order> orders = this.orderRepository.findByCustomerId(customerId);
        return orders;
    }
}
