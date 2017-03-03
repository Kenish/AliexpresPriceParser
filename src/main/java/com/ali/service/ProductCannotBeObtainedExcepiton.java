package com.ali.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY, reason = "Info cannot be obtained")
public class ProductCannotBeObtainedExcepiton extends RuntimeException {
}
