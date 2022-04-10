package com.nur.readingisgood.services.order;

import com.nur.readingisgood.exceptions.EntityNotFoundException;
import com.nur.readingisgood.exceptions.InvalidRequestException;
import com.nur.readingisgood.exceptions.OutOfStockException;
import com.nur.readingisgood.models.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

class RequestValidationDecorator implements IOrderService {
    private final IOrderService decorated;

    public RequestValidationDecorator(IOrderService decorated) {
        this.decorated = decorated;
    }

    @Override
    public void create(Map<String, Integer> items, String customerId) throws OutOfStockException, EntityNotFoundException, InvalidRequestException {
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            Integer itemCount = entry.getValue();
            if(itemCount <= 0){
                String message = String.format("You can not buy %s book", itemCount);
                throw new InvalidRequestException(message);
            }
        }
        decorated.create(items, customerId);
    }

    @Override
    public Order getBy(String id) throws EntityNotFoundException, InvalidRequestException {
        if(id.isBlank() || id.isEmpty()){
            String message = "Order Id can not be empty";
            throw new InvalidRequestException(message);
        }
        Order result = decorated.getBy(id);
        return result;
    }

    @Override
    public List<Order> listBy(LocalDateTime startDate, LocalDateTime endDate) throws InvalidRequestException {
        if(startDate.isAfter(endDate)){
            String message = "Startdate can not be after the enddate";
            throw new InvalidRequestException(message);
        }
        List<Order> result = decorated.listBy(startDate, endDate);
        return result;
    }
}
