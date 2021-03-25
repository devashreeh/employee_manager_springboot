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
	public Long id;
	public String first_name;
	public String last_name;
	public String email;
	public String password;
	public String address;
	public Date date_of_birth;
	public String company;
	
}
