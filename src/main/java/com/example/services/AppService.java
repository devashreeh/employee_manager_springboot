package com.example.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.entity.Manager;
import com.example.entity.UserModel;

public interface AppService {
	
	public List<Employee> getEmployees() throws Exception;
	
	public List<Manager> getManager() throws Exception;
	
	public Optional getEmployeeById(Long id) throws Exception;
	
    public List<Employee> getEmployeeByManagerId(Integer id) throws Exception;
	
	public Object saveEmployee(Employee userDetails) throws Exception;
	
	public Object saveManager(Manager requestManagerDetails) throws Exception;
	
	public void deleteEmployee(Long id) throws Exception;
	
	public Employee updateEmployee(Employee requestUserDetails,Long id) throws Exception;

	public Object signUp(UserModel usersModel,HttpServletRequest request) throws Exception;

	public Object login(UserModel usersModel,HttpServletRequest request) throws Exception;
	
}
