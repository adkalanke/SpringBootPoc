package com.yash.employeemanagement.service;

import java.util.List;

import com.yash.employeemanagement.dto.EmployeeDTO;


public interface EmployeeService {
	
	EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
	List<EmployeeDTO> getEmployees();
	EmployeeDTO getEmployeeById(Long id);
	String deleteEmployee(Long id);
	EmployeeDTO updateEmployee(Long id,EmployeeDTO employeeDTO);

}
