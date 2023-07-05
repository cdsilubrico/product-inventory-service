package com.product.service.exception.handler;

import com.product.service.dto.exception.Error;
import com.product.service.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.product.service.constant.Constant.UNKNOWN_ERROR;

@ControllerAdvice
@Slf4j
public class Generic {
    @ExceptionHandler(java.lang.Exception.class)
    public ResponseEntity<Error> handleGenericException() {
        return ExceptionUtil.errorResponse(UNKNOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
