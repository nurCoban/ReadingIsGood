package com.nur.readingisgood.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends Exception{
    private String exceptionId;

    public ApplicationException(String exceptionId, String message){
        super(message);
        this.exceptionId = exceptionId;
    }
}
