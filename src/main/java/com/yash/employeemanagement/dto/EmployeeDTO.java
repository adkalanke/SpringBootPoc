package com.yash.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
	

	
	private Long id;


	private String name;

	private String email;
	
	
	private String mobileNo;

	
	private String companyName;


	private String address;

	
	private Integer age;


	private Double salary;


}
