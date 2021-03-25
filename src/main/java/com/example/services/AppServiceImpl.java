package com.example.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.entity.Employee;
import com.example.entity.Manager;
import com.example.models.ResponseModel;
import com.example.repository.EmployeeRepository;
import com.example.repository.ManagerRepository;

@Service
public class AppServiceImpl implements AppService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ManagerRepository  managerRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	private Object ResponseHandler = new ResponseModel() ;

	public List<Employee> getEmployees() throws Exception {
		try {
			return (List<Employee>)employeeRepository.findAll();
		}	
		catch(Exception e){
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
		}
		
	}
	
	public List<Manager> getManager() throws Exception {
		try {
			return (List<Manager>)managerRepository.findAll();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	
	public Optional getEmployeeById(Long id) throws Exception {
		try {
			return employeeRepository.findById(id);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	
	public Object saveEmployee(Employee requestUserDetails) throws Exception {
		try {
			return employeeRepository.save(requestUserDetails);
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
		}
		
	}
	
	public Object saveManager(Manager requestManagerDetails) throws Exception {
		try {
			return managerRepository.save(requestManagerDetails);
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	
   public Employee updateEmployee(Employee requestEmployeeDetails,Long id) throws Exception {
	   try {
	    Object employee = employeeRepository.findById(id);
	   	employee = requestEmployeeDetails;
	   	return (Employee) employeeRepository.save(employee);
	   }
	   catch(Exception e){
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
	   }
   }


	public void deleteEmployee(@PathVariable Long id) throws Exception {
		 try {
			 employeeRepository.deleteById(id);
		 }
		 catch(Exception e){
			 System.out.println(e.getMessage());
			 throw new Exception(e.getMessage());
		 }
	}

	
	public List<Employee> getEmployeeByManagerId(Integer id) throws Exception {
		try {
			return  (List<Employee>)employeeRepository.getEmployeeByManagerId(id);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
			
		}
	}
	
}
