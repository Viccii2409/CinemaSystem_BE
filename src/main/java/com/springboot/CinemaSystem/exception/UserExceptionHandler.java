package com.springboot.CinemaSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class UserExceptionHandler {
//    không tìm thấy thực thể
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerNotFoundException(NotFoundException ex, WebRequest request){
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

//    Ngoại lệ tùy chỉnh cho thất bại khi thêm hoặc cập nhật dữ liệu
    @ExceptionHandler(DataProcessingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerDataProcessingException(DataProcessingException ex, WebRequest request){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
