package com.example.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Employee;
 
@Repository
public interface EmployeeRepository<T, ID extends Serializable> extends JpaRepository<Employee,Integer>{
	@Query(value="SELECT * FROM employees WHERE manager_id = ?1",nativeQuery = true)
	List<Employee> getEmployeeByManagerId(Integer id);
}