package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.entity.Manager;
import com.example.models.ResponseModel;
import com.example.services.AppService;

@RestController
public class AppController{
	
	@Autowired
    AppService appService;
    
    private Object ResponseHandler = new ResponseModel();
    
    @GetMapping("/employeeList")
	public ResponseEntity<Object> getEmployees() throws Exception {
    	return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.getEmployees());
	}
	
    @GetMapping("/managerList")
	public ResponseEntity<Object> getManager() throws Exception {
    	return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.getManager());
	}
	
    @GetMapping("/employeeById/{id}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) throws Exception {
    	return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.getEmployeeById(id));
	}
	
    @PostMapping("/saveEmployee")
	public ResponseEntity<Object> saveEmployee(@RequestBody Employee requestUserDetails) throws Exception {
    	return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.saveEmployee(requestUserDetails));
	}
	
	@PostMapping("/saveManager")
	public ResponseEntity<Object> saveManager(@RequestBody Manager requestManagerDetails) throws Exception {
		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.saveManager(requestManagerDetails));
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) throws Exception {
		appService.deleteEmployee(id);
		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "Employee deleted successfully!","");
	}

	@GetMapping("/getEmployeeByManagerId/{id}")
	public ResponseEntity<Object> getEmployeeByManagerId(@PathVariable Integer id) throws Exception {
		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.getEmployeeByManagerId(id));
	}
	
	@PostMapping("/updateEmployee/{id}")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee requestUserDetails,@PathVariable Long id) throws Exception {
		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.updateEmployee(requestUserDetails,id));
	}
	
}
