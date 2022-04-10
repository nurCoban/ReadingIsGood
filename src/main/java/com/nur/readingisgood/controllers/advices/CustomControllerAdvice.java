package com.nur.readingisgood.controllers.advices;

import com.nur.readingisgood.dtos.ErrorResponseDto;
import com.nur.readingisgood.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
class CustomControllerAdvice {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomErrorExceptions(
            Exception e
    ) {
        ApplicationException applicationException = (ApplicationException) e;

        HttpStatus status = HttpStatus.BAD_REQUEST;

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        applicationException.printStackTrace(printWriter);

        return new ResponseEntity<>(
                new ErrorResponseDto(
                        applicationException.getExceptionId(),
                        applicationException.getMessage()
                ),
                status
        );
    }
}
