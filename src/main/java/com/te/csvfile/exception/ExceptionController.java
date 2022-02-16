package com.te.csvfile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
	@ExceptionHandler(ExceptionClass.class)
	public ResponseEntity<?> getException(ExceptionClass exceptionClass) {
		return new ResponseEntity<>(exceptionClass.getMessage(), HttpStatus.NOT_FOUND);
	}
}
