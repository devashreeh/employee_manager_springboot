package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.entity.Manager;

public interface AppService {
	
	public List<Employee> getEmployees();
	
	public ResponseEntity<Object> getManager();
	
	public ResponseEntity<Object> getEmployeeById(Long id);
	
    public List<Employee> getEmployeeByManagerId(Integer id) throws Exception;
	
	public ResponseEntity<Object> saveEmployee(Employee userDetails);
	
	public ResponseEntity<Object> saveManager(Manager requestManagerDetails);
	
	public ResponseEntity<Object> deleteEmployee(Long id);
}
