package com.nur.readingisgood.dtos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto extends ResponseDto{
    private String errorId;
    private String errorMessage;

    public ErrorResponseDto(String errorId, String errorMessage) {
        setSuccess(false);
        this.errorId = errorId;
        this.errorMessage = errorMessage;
    }
}
