package com.nur.readingisgood.controllers;

import com.nur.readingisgood.dtos.StatisticDto;
import com.nur.readingisgood.dtos.SuccessResponseDto;
import com.nur.readingisgood.models.MonthlyOrderStatistic;
import com.nur.readingisgood.services.StatisticsService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/statistic")
public class StatisticsController {
    private final StatisticsService statisticsService;
    private final ModelMapper mapper;

    public StatisticsController(StatisticsService statisticsService, ModelMapper mapper) {
        this.statisticsService = statisticsService;
        this.mapper = mapper;
    }

    @GetMapping
    public SuccessResponseDto<List<StatisticDto>> getBy(String customerId) {
        List<MonthlyOrderStatistic> statistic = statisticsService.getBy(customerId);
        List<StatisticDto> result = statistic.stream()
                .map(s -> this.mapper.map(s, StatisticDto.class)).collect(Collectors.toList());

        SuccessResponseDto<List<StatisticDto>> response = new SuccessResponseDto<>(result);
        return response;
    }
}
