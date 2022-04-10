package com.nur.readingisgood.services.book;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BookServiceConfiguration {
    @Bean
    @Primary
    IBookService getDecoratedBookService(final DefaultBookService decorated) {
        return new RequestValidationDecorator(decorated);
    }
}
