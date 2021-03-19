package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.entity.Manager;

@Service
public interface AppService {
	
	public List<Employee> getEmployees();
	
	public List<Manager> getManager();
	
	public Optional<Employee> getEmployeeById(Long id);
	
//	public Optional<Employee> getEmployeeByManagerId(Long id);
	
	public Employee saveEmployee(Employee userDetails);
	
	public Manager saveManager(Manager requestManagerDetails);
	
	public void deleteEmployee(Long id);

	public Optional<Employee> getEmployeeByManagerId(Long id);
}
