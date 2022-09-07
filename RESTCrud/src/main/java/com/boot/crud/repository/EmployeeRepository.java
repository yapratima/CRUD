package com.boot.crud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boot.crud.entities.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	
}
