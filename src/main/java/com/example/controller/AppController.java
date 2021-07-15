package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.entity.Manager;
import com.example.models.ResponseModel;
import com.example.services.AppService;

import com.example.entity.UserModel;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class AppController{
	
	@Autowired
    AppService appService;
    
    private ResponseModel ResponseHandler = new ResponseModel();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);
    
    @PostMapping("/login")
	public ResponseEntity<Object> login(HttpServletRequest request, @RequestBody UserModel usersModel)
			throws Exception {
			try {
		LOGGER.info("------------ In Login [web service] --------------");
		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.login(usersModel, request));
			}
			catch(Exception e){
    		LOGGER.error("==================Error while fetch employee list "+e.getMessage()+"=================");
			throw new Exception(e.getMessage());
    	}

	}
    
    @PostMapping("/signup")
	public ResponseEntity<Object> createNewUser(HttpServletRequest request, @RequestBody UserModel usersModel)
			throws Exception {
			try {
		LOGGER.info("------------ In signup [web service] --------------");
		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.signUp(usersModel, request));
			}catch(Exception e){
    		LOGGER.error("==================Error while fetch employee list "+e.getMessage()+"=================");
			throw new Exception(e.getMessage());
    	}
	}
    
    @GetMapping("/employeeList")
	public ResponseEntity<Object> getEmployees() throws Exception {
    	try {
    		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.getEmployees());
    	}
    	catch(Exception e){
    		LOGGER.error("==================Error while fetch employee list "+e.getMessage()+"=================");
			throw new Exception(e.getMessage());
    	}
    	
	}
	
    @GetMapping("/managerList")
	public ResponseEntity<Object> getManager() throws Exception {
    	try {
    		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.getManager());
    	}
    	catch(Exception e){
    		LOGGER.error("==================Error while fetch manager list "+e.getMessage()+"=================");
			throw new Exception(e.getMessage());
    	}
	}
	
    @GetMapping("/employeeById/{id}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) throws Exception {
    	try {
    		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.getEmployeeById(id));
    	}
    	catch(Exception e){
    		LOGGER.error("==================Error while fetching employee list by ID "+e.getMessage()+"=================");
			throw new Exception(e.getMessage());
    	}
	}
	
    @PostMapping("/saveEmployee")
	public ResponseEntity<Object> saveEmployee(@RequestBody Employee requestUserDetails) throws Exception {
    	try {
    		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.saveEmployee(requestUserDetails));
    	}
    	catch(Exception e){
    		LOGGER.error("==================Error while saving employee "+e.getMessage()+"=================");
			throw new Exception(e.getMessage());
    	}
	}
	
	@PostMapping("/saveManager")
	public ResponseEntity<Object> saveManager(@RequestBody Manager requestManagerDetails) throws Exception {
		try {
			return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.saveManager(requestManagerDetails));
		}
		catch(Exception e) {
			LOGGER.error("==================Error while saving manager "+e.getMessage()+"=================");
			throw new Exception(e.getMessage());
		}
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) throws Exception {
		try {
			appService.deleteEmployee(id);
			return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "Employee deleted successfully!","");
		}
		catch(Exception e) {
			LOGGER.error("==================Error while deleting employee "+e.getMessage()+"=================");
			throw new Exception(e.getMessage());
		}
	}

	@GetMapping("/getEmployeeByManagerId/{id}")
	public ResponseEntity<Object> getEmployeeByManagerId(@PathVariable Integer id) throws Exception {
		try {
			return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.getEmployeeByManagerId(id));
		}catch(Exception e) {
			LOGGER.error("==================Error while fetching employee by manager Id "+e.getMessage()+"=================");
			throw new Exception(e.getMessage());
		}
	}
	
	@PostMapping("/updateEmployee/{id}")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee requestUserDetails,@PathVariable Long id) throws Exception {
		try {
			return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "success",appService.updateEmployee(requestUserDetails,id));
		}catch(Exception e) {
			LOGGER.error("==================Error while updating employee "+e.getMessage()+"=================");
			throw new Exception(e.getMessage());
		}
	}
	
}
