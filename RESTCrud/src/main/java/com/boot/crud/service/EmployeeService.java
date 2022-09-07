package com.boot.crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.crud.entities.Employee;
import com.boot.crud.repository.EmployeeRepository;

@Service  
public class EmployeeService   
{  
	@Autowired  
	EmployeeRepository repository;  
	
	//parameterized constructor
	public EmployeeService(EmployeeRepository repo) {
		this.repository=repo;
	}
	
	//getting all books record by using the method findaAll() of CrudRepository  
	public List<Employee> getAllEmployees()   
	{  
		List<Employee> employees = new ArrayList<Employee>();  
		repository.findAll().forEach(employee -> employees.add(employee));  
		return employees;  
	}  
	
	//getting a specific record by using the method findById() of CrudRepository  
	public Employee getEmployeeById(int id)   
	{  
		return repository.findById(id).get();  
	}  
	
	//saving a specific record by using the method save() of CrudRepository  
	public void save(Employee employee)   
	{  
		repository.save(employee);  
	}  
	
	//deleting a specific record by using the method deleteById() of CrudRepository  
	public void delete(int id)   
	{  
		repository.deleteById(id);  
	}  
	
	//updating a record  
	public Employee update(Employee employees, int id)   
	{  
		Employee employee=repository.findById(id).get();
		employee.setName(employees.getName());
		employee.setDepartment(employees.getDepartment());
		employee.setDob(employees.getDob());
		return repository.save(employee); 
		
		
	}  
	
	
}  