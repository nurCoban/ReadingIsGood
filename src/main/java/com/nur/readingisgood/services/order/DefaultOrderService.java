package com.nur.readingisgood.services.order;

import com.nur.readingisgood.exceptions.EntityNotFoundException;
import com.nur.readingisgood.exceptions.OutOfStockException;
import com.nur.readingisgood.models.Book;
import com.nur.readingisgood.models.Order;
import com.nur.readingisgood.models.OrderLine;
import com.nur.readingisgood.models.Stock;
import com.nur.readingisgood.repositories.BookRepository;
import com.nur.readingisgood.repositories.OrderRepository;
import com.nur.readingisgood.repositories.StockRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
class DefaultOrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final StockRepository stockRepository;
    private final BookRepository bookRepository;

    public DefaultOrderService(OrderRepository orderRepository, StockRepository stockRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.stockRepository = stockRepository;
        this.bookRepository = bookRepository;
    }

    public void create(Map<String, Integer> items, String customerId) throws OutOfStockException, EntityNotFoundException {
        List<Stock> stocks = new ArrayList<>();
        Order order = new Order(customerId);

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String bookId = entry.getKey();
            Optional<Book> optionalBook = this.bookRepository.findById(bookId);

            if (optionalBook.isEmpty()) {
                String message = String.format("Book with %s id not found.", bookId);
                throw new EntityNotFoundException(message);
            }

            Book book = optionalBook.get();
            Stock stock = this.stockRepository.findByItemId(bookId);

            Integer initialStock = stock.getCount();
            Integer purchaseCount = entry.getValue();

            if (initialStock >= purchaseCount) {
                stock.setCount(initialStock - purchaseCount);
                stocks.add(stock);

                OrderLine orderLine = new OrderLine();
                orderLine.setCount(purchaseCount);
                orderLine.setItem(book);

                order.getLines().put(bookId, orderLine);
            } else {
                String message = String.format("There is no available stock for itemId=%s name=%s", bookId, book.getName());
                throw new OutOfStockException(message);
            }
        }

        this.stockRepository.saveAll(stocks);
        this.orderRepository.insert(order);
    }

    public Order getBy(String id) throws EntityNotFoundException {
        Optional<Order> order = this.orderRepository.findById(id);
        if(order.isEmpty()){
            String message = String.format("Order with %s not found", id);
            throw new EntityNotFoundException(message);
        }
        return order.get();
    }

    public List<Order> listBy(LocalDateTime startDate, LocalDateTime endDate){
        return this.orderRepository.findOrderCreatedBetween(startDate, endDate);
    }
}
