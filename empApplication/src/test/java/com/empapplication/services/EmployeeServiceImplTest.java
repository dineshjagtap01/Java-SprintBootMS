package com.empapplication.services;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.empapplication.entities.Employees;
import com.empapplication.repository.EmployeeRepo;



public class EmployeeServiceImplTest {
	
	@Mock
	private EmployeeRepo employeeRepo;
	
	
	EmployeeService employeeService;
	AutoCloseable autoCloseable;//to close the unwanted resource after entire class finished
	Employees employees;
	
	@BeforeEach
	void setUp() {
		autoCloseable = MockitoAnnotations.openMocks(this);//for this class  test class 
		employeeService = new EmployeeServiceImpl(employeeRepo);
		employees = new Employees(1,"Subramanyam Jadhav","subramnyam.jadhav@example.com","B",2024,"Trainee");
	}
	
	@AfterEach
	void tearDown()throws Exception {
		autoCloseable.close();
	}
	
	@Test
	void testAddEmployee() {
		when(employeeRepo.save(any())).thenReturn(employees);
		
		Employees addEmployees = employeeService.addEmployee(new Employees());
		assertNotNull(addEmployees);
		assertEquals(1, addEmployees.getEmpId());
		
		verify(employeeRepo,times(1)).save(any());
	}
	
	@Test
	void testGetAllEmployees() {
		List<Employees> employeeList = new ArrayList<>();
		employeeList.add(employees);
		
		when(employeeRepo.findAll()).thenReturn(employeeList);
		
		List<Employees> allEmployees = employeeService.getAllEmployees();
		assertNotNull(allEmployees);
		assertFalse(allEmployees.isEmpty());
		assertEquals(1, allEmployees.size());
		
		verify(employeeRepo,times(1)).findAll();
	}
	@Test
	void testGetEmpByIdFound() {
		when(employeeRepo.findByEmpId(1)).thenReturn(employees);
		
		Employees foundEmployees = employeeService.getEmpById(1);
		assertNotNull(foundEmployees);
		assertEquals(1, foundEmployees.getEmpId());
		
		verify(employeeRepo,times(1)).findByEmpId(1);
		
	}
	@Test
	void testGetEmpByIdNotFound() {
		when(employeeRepo.findByEmpId(2)).thenReturn(null);
		
		Employees foundEmployees = employeeService.getEmpById(2);
		assertNull(foundEmployees);
		verify(employeeRepo,times(1)).findByEmpId(1);
		
	}
	@Test
	void testDeleteEmpById() {
		employeeService.deleteEmpById(1);
		verify(employeeRepo,times(1)).deleteById(1);
	}
	@Test
	void testUpdateEmployee() {
		
		when(employeeRepo.save(any())).thenReturn(employees);
		
		Employees updatedEmployees  = employeeService.updateEmployee(new Employees());
		assertNotNull(updatedEmployees);
		assertEquals(1, updatedEmployees.getEmpId());
		
		verify(employeeRepo,times(1)).save(any());
	}
}
