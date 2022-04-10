package com.nur.readingisgood.services.customer;

import com.nur.readingisgood.exceptions.InvalidRequestException;
import com.nur.readingisgood.models.Customer;
import com.nur.readingisgood.models.Order;
import com.nur.readingisgood.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

class RequestValidationDecorator implements ICustomerService {
    private final ICustomerService decorated;
    private final CustomerRepository customerRepository;

    public RequestValidationDecorator(ICustomerService decorated, CustomerRepository customerRepository) {
        this.decorated = decorated;
        this.customerRepository = customerRepository;
    }

    @Override
    public void register(Customer customer) throws InvalidRequestException {
        Optional<Customer> result = this.customerRepository.findByEmail(customer.getEmail());
        if(result.isPresent()){
            String message = String.format("%s this email is already exist", customer.getEmail());
            throw new InvalidRequestException(message);
        }
        this.decorated.register(customer);
    }

    @Override
    public List<Order> getOrders(String customerId) {
        List<Order> orders = this.decorated.getOrders(customerId);
        return orders;
    }
}
