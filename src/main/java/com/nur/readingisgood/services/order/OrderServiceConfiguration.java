package com.nur.readingisgood.services.order;

import com.nur.readingisgood.services.locker.ILockerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class OrderServiceConfiguration {
    @Bean
    @Primary
    IOrderService getDecoratedOrderService(final DefaultOrderService decorated, ILockerService lockerService) {
        IOrderService result = new LockDecorator(decorated, lockerService);
        result = new RequestValidationDecorator(result);

        return result;
    }
}
