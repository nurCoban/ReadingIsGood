package com.nur.readingisgood.controllers;

import com.nur.readingisgood.dtos.CustomerDto;
import com.nur.readingisgood.dtos.OrderDto;
import com.nur.readingisgood.dtos.ResponseDto;
import com.nur.readingisgood.dtos.SuccessResponseDto;
import com.nur.readingisgood.exceptions.InvalidRequestException;
import com.nur.readingisgood.models.Customer;
import com.nur.readingisgood.models.Order;
import com.nur.readingisgood.services.customer.ICustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final ICustomerService customerService;
    private final ModelMapper mapper;

    public CustomerController(ICustomerService customerService, ModelMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @PostMapping("/register")
    public ResponseDto register(@RequestBody CustomerDto customerDto) throws InvalidRequestException {
        Customer customer = this.mapper.map(customerDto, Customer.class);
        this.customerService.register(customer);

        return SuccessResponseDto.OK;
    }

    @GetMapping("/{id}/orders")
    public SuccessResponseDto<List<OrderDto>> getOrders(@PathVariable String id) {
        List<Order> orders = this.customerService.getOrders(id);
        List<OrderDto> result = orders.stream().map(o -> this.mapper.map(o, OrderDto.class)).collect(Collectors.toList());

        SuccessResponseDto<List<OrderDto>> response = new SuccessResponseDto<>(result);
        return response;
    }
}
