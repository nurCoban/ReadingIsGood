package com.nur.readingisgood.services.book;

import com.nur.readingisgood.models.Book;
import com.nur.readingisgood.models.Stock;
import com.nur.readingisgood.repositories.BookRepository;
import com.nur.readingisgood.repositories.StockRepository;
import org.springframework.stereotype.Component;

@Component
class DefaultBookService implements IBookService {
    private final BookRepository bookRepository;
    private final StockRepository stockRepository;

    public DefaultBookService(BookRepository bookRepository, StockRepository stockRepository) {
        this.bookRepository = bookRepository;
        this.stockRepository = stockRepository;
    }

    public void add(Book book){
        this.bookRepository.insert(book);

        Stock stock = new Stock();
        stock.setCount(0);

        String bookId = book.getId();
        stock.setItemId(bookId);

        this.stockRepository.insert(stock);
    }

    public void updateStock(String bookId, Integer count){
        Stock stock = this.stockRepository.findByItemId(bookId);
        stock.setCount(count);
        this.stockRepository.save(stock);
    }
}
