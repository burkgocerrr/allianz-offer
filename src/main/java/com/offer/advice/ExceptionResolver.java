package com.offer.advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler//Başka bir exception olursa buraya düşer.
    public final ResponseEntity<ExceptionResponse> exception (Exception exception, HttpServletRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(LocalDateTime.now(),"1001",exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);
    }
}
