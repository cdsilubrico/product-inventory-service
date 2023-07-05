package com.product.service.exception.handler;

import com.product.service.dto.exception.DataError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;

import static com.product.service.constant.Constant.INVALID_INPUT;

@RestControllerAdvice
@Slf4j
public class Validation {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(final MethodArgumentNotValidException exception) {

        log.error(INVALID_INPUT);

        final List<Object> dataErrors = Collections.singletonList(exception.getFieldErrors()
                .stream()
                .map(dataError -> new DataError(dataError.getDefaultMessage()))
                .toList());

        return new ResponseEntity<>(dataErrors, HttpStatus.BAD_REQUEST);
    }
}
