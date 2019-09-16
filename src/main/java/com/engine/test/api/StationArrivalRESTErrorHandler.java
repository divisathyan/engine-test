package com.engine.test.api;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class StationArrivalRESTErrorHandler extends ResponseEntityExceptionHandler{

	   @ExceptionHandler(Exception.class)
	   protected ResponseEntity<Object> handleTrainDataNotFound(Exception ex) {
	       StationRESTError apiError = new StationRESTError(HttpStatus.NOT_FOUND);
	       apiError.setMessage(ex.getMessage());
	       return new ResponseEntity<>(apiError, apiError.getStatus());
	   }
	   	   
}
