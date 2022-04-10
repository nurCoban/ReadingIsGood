package com.nur.readingisgood.dtos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponseDto<T> extends ResponseDto {
    public final static SuccessResponseDto OK = new SuccessResponseDto();

    private T data;

    public SuccessResponseDto(T data) {
        setSuccess(true);
        setData(data);
    }

    protected SuccessResponseDto() {
        setSuccess(true);
    }
}
