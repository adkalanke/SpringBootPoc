package com.yash.employeemanagement.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yash.employeemanagement.constant.Constant;
import com.yash.employeemanagement.dto.EmployeeDTO;
import com.yash.employeemanagement.entity.Employee;
import com.yash.employeemanagement.exception.EmployeeNotFoundException;
import com.yash.employeemanagement.exception.EmployeeServiceBusinessException;
import com.yash.employeemanagement.repository.EmployeeRepository;
import com.yash.employeemanagement.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

		try {
			log.info("EmployeeService:createEmployee execution started.");
			Employee employee = modelMapper.map(employeeDTO, Employee.class);
			log.debug("EmployeeService:createEmployee request parameters {}", employeeDTO);
			employee = employeeRepository.save(employee);
			employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
			log.debug("EmployeeService:createEmployee received response from Database {}", employeeDTO);
		} catch (Exception exception) {
			log.error("Exception occurred while persisting Employee to database , Exception message {}",
					exception.getMessage());
			throw new EmployeeServiceBusinessException(exception.getMessage());
		}
		log.info("EmployeeService:createEmployee execution ended.");
		return employeeDTO;
	}

	@Override
	public List<EmployeeDTO> getEmployees() {
		List<EmployeeDTO> employeeDTOS = null;
		try {
			log.info("EmployeeService:getEmployee execution started.");
			List<Employee> employeeList = employeeRepository.findAll();
			if (!employeeList.isEmpty()) {
				employeeDTOS = employeeList.stream().map(e -> modelMapper.map(e, EmployeeDTO.class))
						.collect(Collectors.toList());
			} else {
				employeeDTOS = Collections.emptyList();
			}
			log.debug("EmployeeService:getEmployee retrieving all employee from database  {}", employeeDTOS);
		} catch (Exception exception) {
			log.error("Exception occurred while fetching All  Employee from database , Exception message {}",
					exception.getMessage());
			throw new EmployeeServiceBusinessException(exception.getMessage());

		}
		log.info("EmployeService:getEmployees execution ended.");
		return employeeDTOS;
	}

	@Override
	public EmployeeDTO getEmployeeById(Long id) {
		EmployeeDTO employeeDTO;

		try {
			log.info("EmployeeService:getEmployeeById execution started.");
			Employee employee = employeeRepository.findById(id).get();

			employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
			log.debug("EmployeeService:getEmployeeById retrieving employee from database for id {} {}", id,
					employeeDTO);
		} catch (NoSuchElementException exception) {
			log.error("Employee is not found with id {}", id);
			throw new EmployeeNotFoundException("Employee is not found with id: " + id);
		}

		catch (Exception exception) {
			log.error("Exception occurred while retrieving employee {} from database , Exception message {}", id,
					exception.getMessage());
			throw new EmployeeServiceBusinessException(exception.getMessage());

		}
		log.info("EmployeeService:employeeById execution ended.");
		return employeeDTO;

	}

	@Override
	public String deleteEmployee(Long id) {
		try {
			log.info("EmployeeService:deleteEmployee execution started");
			Employee employee = employeeRepository.findById(id)
					.orElseThrow(() -> new EmployeeNotFoundException("Employee is not found with id :" + id));
			employeeRepository.delete(employee);

		} catch (NoSuchElementException exception) {
			log.error("Employee is not found with id {}", id);
			throw new EmployeeNotFoundException("Employee is not found with id: " + id);
		}

		catch (Exception exception) {
			log.error("Exception occurred while deleting employee with id {} from database  , Exception message {}", id,
					exception.getMessage());
			throw new EmployeeServiceBusinessException(exception.getMessage());

		}
		log.info("EmployeeService:deleteEmployee execution ended.");
		return Constant.DELETED + id;
	}

	@Override
	public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {

		try {
			log.info("EmployeeService:updateEmployee execution started");
			Employee employee = employeeRepository.findById(id).get();
			employeeDTO.setId(employee.getId());
			employee = modelMapper.map(employeeDTO, Employee.class);
			employee = employeeRepository.save(employee);
			log.debug("EmployeeService:updateEmployee update employee from database for id {} {}", id, employeeDTO);

		} catch (NoSuchElementException exception) {
			log.error("Employee is not found with id {}", id);
			throw new EmployeeNotFoundException("Employee is not found with id: " + id);
		}

		catch (Exception exception) {
			log.error("Exeption occurred while updating employee with id {} from database: ", id);
			throw new EmployeeServiceBusinessException(exception.getMessage());

		}
		log.info("EmployeeService:updateEmployee execution ended.");
		return employeeDTO;
	}

}
