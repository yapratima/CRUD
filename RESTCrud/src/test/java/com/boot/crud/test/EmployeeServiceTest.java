package com.boot.crud.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.boot.crud.entities.Employee;
import com.boot.crud.repository.EmployeeRepository;
import com.boot.crud.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@Mock
	EmployeeRepository repo;
	
	@InjectMocks
	EmployeeService service;
	
	@BeforeEach
	void setup() {
		this.service=new EmployeeService(this.repo);
	}
	
	@Test
	void getAllEmployeesTest() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse("1992-07-26");
		when(repo.findAll()).thenReturn(Stream
				.of(new Employee(6, "Danile", "Anonymous", date), new Employee(5, "Huy", "anonym", date)).collect(Collectors.toList()));
		assertEquals(2, service.getAllEmployees().size());

	}
	
	@Test
	void getEmployeeByIdTest() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse("2001-07-21");
		
		Employee emp=new Employee(8, "Huy", "anonym", date);		
		when(repo.findById(8)).thenReturn(Optional.of(emp));
	    assertThat(service.getEmployeeById(8)).isEqualTo(emp);
	}

	@Test
	void saveTest() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse("2001-07-21");
		
		Employee emp = new Employee(999, "Pranya",  "Pune", date);
		service.save(emp);
		verify(repo,times(1)).save(emp);
	}

	@Test
	void deleteTest() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse("2001-07-21");
		
		Employee emp = new Employee(9, "Pranya",  "Pune", date);
		service.delete(9);
		verify(repo, times(1)).deleteById(9);
	}

	@Test
	void updateTest() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse("2001-07-21");
		
		Employee emp1 = new Employee(99, "Pranya",  "Pune", date);
		when(repo.findById(99)).thenReturn(Optional.of(emp1));
		Employee emp2 = new Employee(99, "The Atomic Habits",  "James Clear", date);
		assertThat(service.update(emp2, 99)).isEqualTo(repo.save(emp2));
		
	}

	
	
}
