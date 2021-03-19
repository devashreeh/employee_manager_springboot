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
@Table(name = "manager")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
	
	
	@Id 
	@GeneratedValue
	Long id;
	String first_name;
	String last_name;
	String email;
	String password;
	String address;
	Date date_of_birth;
	String company;
	
}
