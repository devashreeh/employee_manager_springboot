package com.example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Employee;
 
@Repository
public interface EmployeeRepository<T, ID extends Serializable> extends JpaRepository<Employee,Integer>{	
	
}