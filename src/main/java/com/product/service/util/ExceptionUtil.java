package com.product.service.util;

import com.product.service.dto.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ExceptionUtil {
    private ExceptionUtil() {
    }
    public static ResponseEntity<Error> errorResponse(final String message, final HttpStatus statusCode) {
        return new ResponseEntity<>(new Error(message, new Date()), statusCode);
    }
}
