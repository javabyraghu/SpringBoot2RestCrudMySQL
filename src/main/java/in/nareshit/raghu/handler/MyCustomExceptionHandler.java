package in.nareshit.raghu.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.nareshit.raghu.error.ErrorType;
import in.nareshit.raghu.exception.EmployeeNotFoundException;

@RestControllerAdvice
public class MyCustomExceptionHandler {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorType> handleEmployeeNotFound(
			EmployeeNotFoundException enfe) 
	{
		return new ResponseEntity<ErrorType>(
				new ErrorType(
						new Date().toString(), 
						"EMPLOYEE", 
						enfe.getMessage(), 
						"PROCESSING ISSUE"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
