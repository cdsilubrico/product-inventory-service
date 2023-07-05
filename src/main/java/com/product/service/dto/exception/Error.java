package com.product.service.dto.exception;

import java.util.Date;

public record Error(String message, Date timeStamp) {
}
