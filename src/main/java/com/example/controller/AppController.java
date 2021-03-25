package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.entity.Manager;
import com.example.models.ResponseModel;
import com.example.services.AppService;

@RestController
public class AppController{
	
	@Autowired
    private AppService appService;
    
	@Autowired
    private Object ResponseHandler = new ResponseModel() ;
    
    @GetMapping("/employeeList")
	public ResponseEntity<Object> getEmployees() {
    	return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.getEmployees());
	}
	
    @GetMapping("/managerList")
	public ResponseEntity<Object> getManager() {
        return (ResponseEntity<Object>) appService.getManager();
	}
	
    @GetMapping("/employeeById/{id}")
	public ResponseEntity<Object> getEmployeeById(Long id) {
        return (ResponseEntity<Object>) appService.getEmployeeById(id);
	}
	
    @PostMapping("/saveEmployee")
	public ResponseEntity<Object> saveEmployee(Employee userDetails) {
        return appService.saveEmployee(userDetails);
	}
	
	@PostMapping("/saveManager")
	public ResponseEntity<Object> saveManager(Manager requestManagerDetails) {
        return appService.saveManager(requestManagerDetails);
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Object> deleteEmployee(Long id) {
		return appService.deleteEmployee(id);
	}

	@GetMapping("/getEmployeeByManagerId/{id}")
	public ResponseEntity<Object> getEmployeeByManagerId(@PathVariable Integer id) throws Exception {
		System.out.println(appService.getEmployeeByManagerId(id));
		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.getEmployeeByManagerId(id));
	}
	
}
