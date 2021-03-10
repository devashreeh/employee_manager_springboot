package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	Integer id;
	
	@Column(name="employee_name")
	String employee_name;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getEmployee_name() {
		return employee_name;
	}


	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
}
