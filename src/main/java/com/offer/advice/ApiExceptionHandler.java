package com.offer.advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)//Başka bir exception olursa buraya düşer.
    public final ResponseEntity<ExceptionResponse> exception (Exception exception, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(LocalDateTime.now(),"1001",exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);
    }
}
