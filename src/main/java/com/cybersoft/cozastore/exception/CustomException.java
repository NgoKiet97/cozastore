package com.cybersoft.cozastore.exception;

import com.cybersoft.cozastore.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomException {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleException(Exception e){
        BaseResponse response = new BaseResponse();
        response.setStatusCode(500);
        response.setMessage(e.getMessage());

        return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
