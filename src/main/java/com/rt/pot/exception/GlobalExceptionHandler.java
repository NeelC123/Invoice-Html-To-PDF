package com.rt.pot.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataGettingException.class)
	public ResponseEntity<ExceptionModel> dataGettingException(Exception ex) {
		ExceptionModel exceptionModel = new ExceptionModel();

		exceptionModel.setHttpStatus(HttpStatus.NOT_FOUND);
		exceptionModel.setMessage(ex.getMessage());

		return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		return ResponseEntity.badRequest().body("Validation failed: " + errors);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGlobalException(Exception exception) {
	
		
		return  ResponseEntity.badRequest().body("Validation Failed "+exception.getMessage());
	}

}