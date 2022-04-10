package com.nur.readingisgood.services.customer;

import com.nur.readingisgood.repositories.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CustomerServiceConfiguration {
    @Bean
    @Primary
    ICustomerService getDecoratedCustomerService(final DefaultCustomerService decorated, CustomerRepository customerRepository) {
        return new RequestValidationDecorator(decorated, customerRepository);
    }
}
