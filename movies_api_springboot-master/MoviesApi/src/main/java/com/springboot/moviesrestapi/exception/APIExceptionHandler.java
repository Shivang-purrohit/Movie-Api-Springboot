package com.springboot.moviesrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler {
	
	@ExceptionHandler(MovieExistsException.class)
	public ResponseEntity<?>handleMovieExistsException( MovieExistsException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MovieDoesNotExistsException.class)
	public ResponseEntity<?>handleMovieDoesNotExistsException(MovieDoesNotExistsException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
