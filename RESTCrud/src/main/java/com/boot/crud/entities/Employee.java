package com.boot.crud.entities;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.annotation.CreatedDate;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity  
//defining class name as Table name  
@Table  
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdDateTime"}, allowGetters = true)
public class Employee  
{  
	
	
		@Id  
		@Column(name = "id") 
		private int id;  
		@Column(name = "name")
		private String name;  
		@Column(name = "department")
		private String department;  
		
		@Column(name = "dob", nullable = false) 
		@Temporal(TemporalType.DATE)
		private Date dob; 
		
		@Column(updatable = false, name = "created_date_time") 
		@Temporal(TemporalType.TIMESTAMP)
		@CreatedDate
		private Date createdDateTime; 
		
		public Employee(int id, String name, String department, Date dob) {
			super();
			this.id = id;
			this.name = name;
			this.department = department;
			this.dob = dob;
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public Date getDob() {
//			try {
//				DateTimeFormatter df =DateTimeFormatter.ofPattern("dd-mmm-yyyy");
//				LocalDate date = LocalDate.parse(dob.toString(),df);
//				System.out.println(date);
//				df.format(date);
//				return date;
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
			return dob;
			
		}

		public void setDob(Date dob) {
			this.dob = dob;
		}

		public Date getCreatedDateTime() {
			return createdDateTime;
		}
		
		public Employee() {
			super();
			
		}
		
	}  
