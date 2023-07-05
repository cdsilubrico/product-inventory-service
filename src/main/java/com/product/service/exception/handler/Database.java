package com.product.service.exception.handler;

import com.product.service.dto.exception.Error;
import com.product.service.exception.specific.DuplicateEntry;
import com.product.service.exception.specific.NoRecordFound;
import com.product.service.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.product.service.constant.Constant.DUPLICATE_PRODUCT;
import static com.product.service.constant.Constant.NO_RECORD_FOUND;

@ControllerAdvice
@Slf4j
public class Database extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DuplicateEntry.class)
    public ResponseEntity<Error> handleDuplicateEntry() {

        log.error(DUPLICATE_PRODUCT);

        return ExceptionUtil.errorResponse(DUPLICATE_PRODUCT, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoRecordFound.class)
    public ResponseEntity<Error> handleNoElementFound() {

        log.error(NO_RECORD_FOUND);

        return ExceptionUtil.errorResponse(NO_RECORD_FOUND, HttpStatus.BAD_REQUEST);
    }
}
