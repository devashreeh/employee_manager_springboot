package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.entity.Manager;
import com.example.services.AppService;

@RestController
public class AppController{
	
    private AppService appService;

	public List<Employee> getEmployees() {
        return (List<Employee>) appService.getEmployees();
	}
	
	public List<Manager> getManager() {
        return (List<Manager>) appService.getManager();
	}
	
	public Optional<Employee> getEmployeeById(Long id) {
        return (Optional<Employee>) appService.getEmployeeById(id);
	}
	
	public Employee saveEmployee(Employee userDetails) {
        return appService.saveEmployee(userDetails);
	}
	
	public Manager saveManager(Manager requestManagerDetails) {
        return appService.saveManager(requestManagerDetails);
	}
	
	public void deleteEmployee(Long id) {
		appService.deleteEmployee(id);
	}
	
	public Optional<Employee> getEmployeeByManagerId(Long id) {
        return (Optional<Employee>) appService.getEmployeeByManagerId(id);
	}
	
}
