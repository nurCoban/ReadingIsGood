package com.nur.readingisgood.services;

import com.nur.readingisgood.models.MonthlyOrderStatistic;
import com.nur.readingisgood.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {
    private final OrderRepository orderRepository;

    public StatisticsService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<MonthlyOrderStatistic> getBy(String customerId) {
        List<MonthlyOrderStatistic> result = this.orderRepository.getMonthlyBy(customerId);
        return result;
    }
}
