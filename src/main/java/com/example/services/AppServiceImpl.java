package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.entity.Manager;
import com.example.repository.EmployeeRepository;
import com.example.repository.ManagerRepository;

@RestController
public class AppServiceImpl implements AppService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ManagerRepository  managerRepository;


	@GetMapping("/getEmployees")
	public @ResponseBody List<Employee> getEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}
	
	@GetMapping("/getManager")
	public @ResponseBody List<Manager> getManager() {
		return (List<Manager>) managerRepository.findAll();
	}
	
	@GetMapping("/getEmployee/{id}")
	public @ResponseBody Optional<Employee> getEmployeeById(@PathVariable Long id) {
		return employeeRepository.findById(id);
	}
	
	@PostMapping("/saveEmployee")
	public Employee saveEmployee(@RequestBody Employee requestUserDetails) {
		return (Employee) employeeRepository.save(requestUserDetails);
	}
	
	@PostMapping("/saveManager")
	public Manager saveManager(@RequestBody Manager requestManagerDetails) {
		return (Manager) managerRepository.save(requestManagerDetails);
	}
	

	@DeleteMapping("/delete/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employeeRepository.deleteById(id);
	}


	@GetMapping("/getEmployeeByManagerId/{id}")
    @Query("SELECT * FROM EMPLOYEES WHERE manager_id = :id")
    public Optional<Employee> getEmployeeByManagerId(@Param("id") String id) {
		return employeeRepository.findById(id);
	}


	@Override
	public Optional<Employee> getEmployeeByManagerId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


}
