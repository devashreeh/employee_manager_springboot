package com.example.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.entity.Manager;
import com.example.models.ResponseModel;
import com.example.repository.EmployeeRepository;
import com.example.repository.ManagerRepository;

public class AppServiceImpl implements AppService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ManagerRepository  managerRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	private Object ResponseHandler = new ResponseModel() ;

	public List<Employee> getEmployees() {
		return (List<Employee>)employeeRepository.findAll();
	}
	
	public @ResponseBody ResponseEntity<Object> getManager() {
		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "Success", managerRepository.findAll());
	}
	
	public @ResponseBody ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "Success",  employeeRepository.findById(id));
	}
	
	public ResponseEntity<Object> saveEmployee(@RequestBody Employee requestUserDetails) {
		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "Success", employeeRepository.save(requestUserDetails));
	}
	
	public ResponseEntity<Object> saveManager(@RequestBody Manager requestManagerDetails) {
		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "Success", managerRepository.save(requestManagerDetails));
	}
	

	public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
		employeeRepository.deleteById(id);
		return ((ResponseModel) ResponseHandler).generateResponse(HttpStatus.OK, "Employee deleted successfully!","");
	}

	
	public List<Employee> getEmployeeByManagerId(Integer id) throws Exception {
		System.out.println("here");
		System.out.println(id);
		try {
			return  (List<Employee>)employeeRepository.getEmployeeByManagerId(id);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
			
		}
	}
	
}
