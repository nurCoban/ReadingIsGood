package com.nur.readingisgood.services.book;

import com.nur.readingisgood.exceptions.InvalidRequestException;
import com.nur.readingisgood.models.Book;

class RequestValidationDecorator implements IBookService {
    private final IBookService decorated;

    public RequestValidationDecorator(IBookService decorated) {
        this.decorated = decorated;
    }

    @Override
    public void add(Book book) throws InvalidRequestException {
        if(book.getPrice() < 0){
            String message = "Price can not be below 0";
            throw new InvalidRequestException(message);
        }
        this.decorated.add(book);
    }

    @Override
    public void updateStock(String bookId, Integer count) throws InvalidRequestException {
        if(count < 0){
            String message = "Stock can not be below 0";
            throw new InvalidRequestException(message);
        }
        this.decorated.updateStock(bookId, count);
    }
}
