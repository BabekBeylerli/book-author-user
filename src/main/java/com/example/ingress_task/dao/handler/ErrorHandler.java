package com.example.ingress_task.dao.handler;
import com.example.ingress_task.exception.NotFoundException;
import com.example.ingress_task.model.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class ErrorHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    private ExceptionDto handler(NotFoundException notFoundException) {
        return new ExceptionDto(notFoundException.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionDto handler(MethodArgumentNotValidException exception) {
        return new ExceptionDto(exception.getMessage());
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ExceptionDto handleUnauthorizedException(NotFoundException ex) {
        return new ExceptionDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ExceptionDto handleGeneralException(Exception ex) {
        return new ExceptionDto(ex.getMessage());
    }

}

