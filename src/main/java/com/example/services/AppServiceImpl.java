package com.example.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;

import com.example.entity.Employee;
import com.example.entity.Manager;
import com.example.entity.UserModel;

import com.example.models.ResponseModel;
import com.example.repository.EmployeeRepository;
import com.example.repository.ManagerRepository;

import com.example.repository.RoleRepository;

import com.example.persistence.domain.entity.Role;
import com.example.persistence.domain.entity.User;
import com.example.persistence.repository.UserRepository;
import org.apache.catalina.mapper.Mapper;

@Service
public class AppServiceImpl implements AppService {

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ManagerRepository  managerRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	private Mapper mapper;
	
//	private Object ResponseHandler = new ResponseModel() ;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppServiceImpl.class);

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees() throws Exception {
		try {
			return (List<Employee>)employeeRepository.findAll();
		}	
		catch(Exception e){
			LOGGER.error("-----------------Error: Employees List "+e.getMessage()+"----------------");
			throw new Exception(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Manager> getManager() throws Exception {
		try {
			return (List<Manager>)managerRepository.findAll();
		}
		catch(Exception e){
			LOGGER.error("-----------------Error: Manager List "+e.getMessage()+"----------------");
			throw new Exception(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public Optional getEmployeeById(Long id) throws Exception {
		try {
			return employeeRepository.findById(id);
		}
		catch(Exception e){
			LOGGER.error("-----------------Error: Get employee by Id"+e.getMessage()+"----------------");
			throw new Exception(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public Object saveEmployee(Employee requestUserDetails) throws Exception {
		try {
			return employeeRepository.save(requestUserDetails);
		}catch(Exception e){
			LOGGER.error("-----------------Error while saving employee "+e.getMessage()+"----------------");
			throw new Exception(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public Object saveManager(Manager requestManagerDetails) throws Exception {
		try {
			return managerRepository.save(requestManagerDetails);
		}catch(Exception e){
			LOGGER.error("-----------------Error while saving manager "+e.getMessage()+"----------------");
			throw new Exception(e.getMessage());
		}
	}
	
   @SuppressWarnings("unchecked")
public Employee updateEmployee(Employee requestEmployeeDetails,Long id) throws Exception {
	   try {
	    @SuppressWarnings("unchecked")
		Object employee = employeeRepository.findById(id);
	   	employee = requestEmployeeDetails;
	   	return (Employee) employeeRepository.save(employee);
	   }
	   catch(Exception e){
		   LOGGER.error("-----------------Error while updating employee "+e.getMessage()+"----------------");
		   throw new Exception(e.getMessage());
	   }
   }


	@SuppressWarnings("unchecked")
	public void deleteEmployee(@PathVariable Long id) throws Exception {
		 try {
			 employeeRepository.deleteById(id);
		 }
		 catch(Exception e){
			 LOGGER.error("-----------------Error while deleting employee "+e.getMessage()+"----------------");
			 throw new Exception(e.getMessage());
		 }
	}

	
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeByManagerId(Integer id) throws Exception {
		try {
			return  (List<Employee>)employeeRepository.getEmployeeByManagerId(id);
		}
		catch(Exception e){
			LOGGER.error("-----------------Error while fetching employee by managerID "+e.getMessage()+"----------------");
			throw new Exception(e.getMessage());
			
		}
	}

	@Override
	public Object signUp(UserModel usersModel,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> userData = new HashMap<>();
		LOGGER.info("<-------------- in signup service ------------->");
		try {
			usersModel.setPassword(new BCryptPasswordEncoder().encode(usersModel.getPassword()));
			String sessionToken = createSessionToken(usersModel.getEmail());
			usersModel.setSessionToken(sessionToken);
			usersModel.setEnabled(true);
			User users = userRepository.save(usersModel, User.class);
			users = userRepository.save(users);

//			UserModel savedUserModel = mapper.map(users, UserModel.class);
//			saveUserSession(request, savedUserModel);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		}
		return userData;
	}

	@Override
	public Object login(UserModel usersModel,HttpServletRequest request) throws Exception {
		Map<String, Object> userData = new HashMap<>();

		Optional<User> users = userRepository.findByEmailAndIsDeleted(usersModel.getEmail(), false);
		try {
			if (users.isPresent()) {
				if (users.get().isEnabled()) {

					checkUserPassword(usersModel, users.get());
					String sessionToken = createSessionToken(users.get().getEmail());
//					users.get().setSessionToken(sessionToken);
					UserModel userDetails = userRepository.save(users.get(), UserModel.class);
					userDetails.setPassword(null);

//					saveUserSession(request, userDetails);
				} else {
					throw new Exception();
				}
			} else {
				throw new Exception();
			}
		} catch (HttpClientErrorException e) {
			LOGGER.error(e.getMessage());
			if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
				throw new Exception(e.getMessage());
			else
				throw new Exception(e.getMessage());
		}
		return userData;
	}
	private String createSessionToken(String email) {
		return new BCryptPasswordEncoder().encode(email + new Timestamp(new Date().getTime()));
	}
	
	private void checkUserPassword(UserModel usersModel, User users) throws Exception {
		if (!new BCryptPasswordEncoder().matches(usersModel.getPassword(), users.getPassword())) {
			throw new Exception();
		}
	}

	
//	private void saveUserSession(HttpServletRequest request, UserModel userDetails) {
//		if (request.getHeader("Device-Type") != null || request.getHeader("Device-Token") != null) {
//			Optional<UserSession> userSession = userSessionRepository.findByUserId(userDetails.getId());
//			UserSessionModel userSessionModel = new UserSessionModel();
//			userSessionModel.setDeviceToken(request.getHeader("Device-Token"));
//			userSessionModel.setDeviceType(request.getHeader("Device-Type"));
//			userSessionModel.setUserId(userDetails.getId());
//			userSessionModel.setDeleted(false);
//			if (userSession.isPresent()) {
//				userSessionModel.setId(userSession.get().getId());
//				userSessionModel.setCreatedAt(userSession.get().getCreatedAt());
//			}
//			userSessionRepository.save(mapper.map(userSessionModel, UserSession.class));
//		}
//	}

	
}
