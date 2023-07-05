package com.product.service.exception.handler;

import com.product.service.dto.exception.Error;
import com.product.service.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class Generic {
    @ExceptionHandler(java.lang.Exception.class)
    public ResponseEntity<Error> handleGenericException() {
        return ExceptionUtil.errorResponse("test", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
