package com.cybersoft.cozastore_java21.exception;

import com.cybersoft.cozastore_java21.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalCustomException {

    // Nơi đăng ký các Exception
    @ExceptionHandler(CustomFileNotFoundException.class)
    public ResponseEntity<?> handlerUserNotFound(Exception e){
        BaseResponse response = new BaseResponse();
        response.setStatusCode(500);
        response.setData(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handlerCustomException(Exception e){
        BaseResponse response = new BaseResponse();
        response.setStatusCode(500);
        response.setData(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
