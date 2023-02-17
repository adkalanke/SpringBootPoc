package com.yash.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.employeemanagement.dto.EmployeeDTO;

import com.yash.employeemanagement.service.EmployeeService;

@RestController
@RequestMapping("/employee-management")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employee")
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {

		employeeDTO = employeeService.createEmployee(employeeDTO);

		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);

	}

	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDTO>> getEmployees() {

		return new ResponseEntity<List<EmployeeDTO>>(employeeService.getEmployees(), HttpStatus.OK);

	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
		return new ResponseEntity<EmployeeDTO>(employeeService.getEmployeeById(id), HttpStatus.OK);

	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {

		return new ResponseEntity<String>(employeeService.deleteEmployee(id), HttpStatus.OK);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
		return new ResponseEntity<EmployeeDTO>(employeeService.updateEmployee(id, employeeDTO), HttpStatus.OK);
	}

}
