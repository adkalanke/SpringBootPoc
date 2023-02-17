package com.yash.employeemanagement.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yash.employeemanagement.dto.ExceptionResponseDTO;
import com.yash.employeemanagement.exception.EmployeeNotFoundException;
import com.yash.employeemanagement.exception.EmployeeServiceBusinessException;

@RestControllerAdvice
public class EmployeeServiceExceptionHandler {

	@ExceptionHandler(EmployeeServiceBusinessException.class)
	public ResponseEntity<ExceptionResponseDTO> handleEmployeeServiceBusinessExcetion(
			EmployeeServiceBusinessException exception) {

		ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(exception.getMessage());
		return new ResponseEntity<ExceptionResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ExceptionResponseDTO> handleEmployeeNotFound(EmployeeNotFoundException exception) {

		ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(exception.getMessage());
		return new ResponseEntity<ExceptionResponseDTO>(responseDTO, HttpStatus.NOT_FOUND);

	}

}
