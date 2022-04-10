package com.nur.readingisgood.controllers;

import com.nur.readingisgood.dtos.NewOrderDto;
import com.nur.readingisgood.dtos.OrderDto;
import com.nur.readingisgood.dtos.ResponseDto;
import com.nur.readingisgood.dtos.SuccessResponseDto;
import com.nur.readingisgood.exceptions.EntityNotFoundException;
import com.nur.readingisgood.exceptions.InvalidRequestException;
import com.nur.readingisgood.exceptions.OutOfStockException;
import com.nur.readingisgood.models.Order;
import com.nur.readingisgood.services.order.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final IOrderService orderService;
    private final ModelMapper mapper;

    public OrderController(IOrderService orderService, ModelMapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public ResponseDto create(@RequestBody NewOrderDto newOrder) throws OutOfStockException, EntityNotFoundException, InvalidRequestException {
        Map<String, Integer> items = newOrder.getItems();
        String customerId = newOrder.getCustomerId();
        this.orderService.create(items, customerId);

        return SuccessResponseDto.OK;
    }

    @GetMapping("/byId/{id}")
    public SuccessResponseDto<OrderDto> getBy(@PathVariable String id) throws EntityNotFoundException, InvalidRequestException {
        Order order = this.orderService.getBy(id);
        OrderDto result = this.mapper.map(order, OrderDto.class);

        SuccessResponseDto<OrderDto> response = new SuccessResponseDto<>(result);
        return response;
    }

    @GetMapping("/byRange")
    public SuccessResponseDto<List<OrderDto>> listBy(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) throws InvalidRequestException {
        List<Order> orders = this.orderService.listBy(startDate, endDate);
        List<OrderDto> result = orders.stream()
                .map(o -> this.mapper.map(o, OrderDto.class)).collect(Collectors.toList());

        SuccessResponseDto<List<OrderDto>> response = new SuccessResponseDto<>(result);
        return response;
    }
}
