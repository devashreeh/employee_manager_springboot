package com.example.entity;

import java.sql.Date;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	
	@Id 
	@GeneratedValue
	Long id;
	String emp_id;
	public String first_name;
	public String last_name;
	Date date_of_birth;
	Long mobile;
	String address;
	Integer manager_id;
	Integer isDeleted;

}
