package com.boot.crud.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.crud.entities.Employee;
import com.boot.crud.service.EmployeeService;

@RestController  
@RequestMapping("/employee")
public class EmployeeController   
{  
	@Autowired  
	EmployeeService service;  
	
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	//creating a get mapping that retrieves all the employee detail from the database   
	@GetMapping("/all")  
	private ResponseEntity<List<Employee>> getAllEmployees()   
	{  
		logger.info("getAllEmployees method accessed");
		List<Employee> list= service.getAllEmployees();  
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));
	} 
	
	
	//creating a get mapping that retrieves the detail of a specific employee  
	@GetMapping("get/{id}")  
	private ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id)   
	{  
		
		logger.info("getEmployeeById method called");
		Employee employee= service.getEmployeeById(id);  
		if(employee==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(employee));
	}  
	
	
	//creating a delete mapping that deletes a specified employee  
	@DeleteMapping("delete/{id}")  
	private ResponseEntity<Void> deleteEmployee(@PathVariable("id") int id)   
	{  
		try{
			service.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
		}catch(Exception e) {
			
			logger.error("Exception occured");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}  
	
	
	//creating post mapping that post the employee detail in the database  
	@PostMapping("save")  
	private ResponseEntity<Integer> saveEmployee(@RequestBody Employee employee)   
	{  
		try {
			service.save(employee);  
			return ResponseEntity.ok().body((employee.getId()));  
		}catch(Exception e){
			
			logger.error("Exception occured");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}  
	
	
	//creating put mapping that updates the employee detail   
	@PutMapping("update/{id}")  
	private ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable("id") int id)   
	{  
		try {
			service.update(employee, id);  
			return ResponseEntity.ok().body(employee);
		}catch(Exception e) {
			
			logger.error("Exception occured");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}  
}  
