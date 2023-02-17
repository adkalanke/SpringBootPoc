package com.yash.employeemanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;
	
	@Column(name="mobile_no")
	private String mobileNo;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "address")
	private String address;

	@Column(name = "age")
	private Integer age;

	@Column(name = "salary")
	private Double salary;

}
