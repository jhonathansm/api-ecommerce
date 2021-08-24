package com.ecommerce.resource.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecommerce.exception.NotFoundException;


@ControllerAdvice
public class ResourceExceptionHandle extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<HandleErrors> handleNotFoundException(NotFoundException ex){
		HandleErrors error = new HandleErrors(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<HandleErrors> handleNotFoundException(BadCredentialsException ex){
		HandleErrors error = new HandleErrors(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), new Date());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<HandleErrors> handleAcessDeniedException(AccessDeniedException ex){
		HandleErrors error = new HandleErrors(HttpStatus.FORBIDDEN.value(), ex.getMessage(), new Date());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errorsList = new ArrayList<String>();
		ex.getBindingResult().getAllErrors().forEach(errorApi -> {
			errorsList.add(errorApi.getDefaultMessage());
		});
		String message = "Campos invalidos";
		ApiErrorList errors = new ApiErrorList(HttpStatus.BAD_REQUEST.value(), message, new Date(), errorsList);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class })
	public ResponseEntity<HandleErrors> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		HandleErrors errors = new HandleErrors(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
}
