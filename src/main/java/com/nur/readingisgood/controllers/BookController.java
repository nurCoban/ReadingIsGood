package com.nur.readingisgood.controllers;

import com.nur.readingisgood.dtos.BookDto;
import com.nur.readingisgood.dtos.ResponseDto;
import com.nur.readingisgood.dtos.SuccessResponseDto;
import com.nur.readingisgood.exceptions.InvalidRequestException;
import com.nur.readingisgood.models.Book;
import com.nur.readingisgood.services.book.IBookService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final IBookService defaultBookService;
    private final ModelMapper mapper;

    public BookController(IBookService defaultBookService, ModelMapper mapper) {
        this.defaultBookService = defaultBookService;
        this.mapper = mapper;
    }

    @PostMapping("/add")
    public ResponseDto add(@RequestBody BookDto bookDto) throws InvalidRequestException {
        Book book = this.mapper.map(bookDto, Book.class);
        this.defaultBookService.add(book);

        return SuccessResponseDto.OK;
    }

    @PutMapping("/{id}/updateStock")
    public ResponseDto updateStock(@PathVariable String id, @RequestBody Integer stock) throws InvalidRequestException {
        this.defaultBookService.updateStock(id, stock);

        return SuccessResponseDto.OK;
    }
}
