package com.nur.readingisgood.services.order;

import com.nur.readingisgood.exceptions.EntityNotFoundException;
import com.nur.readingisgood.exceptions.InvalidRequestException;
import com.nur.readingisgood.exceptions.OutOfStockException;
import com.nur.readingisgood.models.Order;
import com.nur.readingisgood.services.locker.ILockerService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

class LockDecorator implements IOrderService {
    private final IOrderService decorated;
    private final ILockerService lockerService;

    public LockDecorator(IOrderService decorated, ILockerService lockerService) {
        this.decorated = decorated;
        this.lockerService = lockerService;
    }

    @Override
    public void create(Map<String, Integer> items, String customerId) throws OutOfStockException, EntityNotFoundException, InvalidRequestException {
        List<String> keys = items.keySet().stream()
                .sorted() // deadlock fixer
                .collect(Collectors.toList());
        List<Lock> locks = new ArrayList<>();

        try {
            for (String key : keys) {
                Lock lock = lockerService.createLock(key);
                lock.lock();

                locks.add(lock);
            }

            this.decorated.create(items, customerId);
        } finally {
            for (Lock lock : locks) {
                lock.unlock();
            }
        }
    }

    @Override
    public Order getBy(String id) throws EntityNotFoundException, InvalidRequestException {
        return this.decorated.getBy(id);
    }

    @Override
    public List<Order> listBy(LocalDateTime startDate, LocalDateTime endDate) throws InvalidRequestException {
        return this.decorated.listBy(startDate, endDate);
    }
}
