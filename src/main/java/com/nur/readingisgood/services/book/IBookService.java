package com.nur.readingisgood.services.book;

import com.nur.readingisgood.exceptions.InvalidRequestException;
import com.nur.readingisgood.models.Book;

public interface IBookService {
    void add(Book book) throws InvalidRequestException;
    void updateStock(String bookId, Integer count) throws InvalidRequestException;
}
