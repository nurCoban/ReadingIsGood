package com.nur.readingisgood.configs;

import com.nur.readingisgood.dtos.OrderDto;
import com.nur.readingisgood.models.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Order, OrderDto> typeMap = modelMapper.createTypeMap(Order.class, OrderDto.class);
        typeMap.addMappings(mapper -> {
            mapper.map(src -> src.getLines().values(),
                    OrderDto::setItems);
        });

        return modelMapper;
    }
}
