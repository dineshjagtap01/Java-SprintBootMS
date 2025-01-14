package com.empapplication.controller;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



import com.empapplication.entities.Employees;
import com.empapplication.services.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(MyController.class)
public class MyControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	Employees employeeOne;
	Employees employeeTwo;
	
	List<Employees> employeesList = new ArrayList<>();
	
	
	@BeforeEach
	void setUp() {
		employeeOne = new Employees(1,"Subramanyam Jadhav","subramnyam.jadhav@example.com","B",2024,"Trainee");
		employeeTwo = new Employees(2,"Yash Markad","yash.markad@example.com","A",2023,"Research&Development");
		employeesList.add(employeeOne);
		employeesList.add(employeeTwo);
	}
	
	@AfterEach
	void tearDown() {
		
	}
	@Test
	void testGetEmpById() throws Exception {
		when(employeeService.getEmpById(1)).thenReturn(employeeOne);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/1")).andExpect(status().isOk())
					.andExpect(jsonPath("$.empId").value(1))
					.andExpect(jsonPath("$.empName").value("Subramanyam Jadhav"))
					.andDo(print());
		verify(employeeService,times(1)).getEmpById(1);
	}
	@Test
	void testGetAllEmployees() throws Exception{
		when(employeeService.getAllEmployees()).thenReturn(employeesList);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employees")).andExpect(status().isOk())
					.andExpect(jsonPath("$[0].empId").value(1))
					.andExpect(jsonPath("$[0].empName").value("Subramanyam Jadhav"))
					.andExpect(jsonPath("$[1].empId").value(2))
					.andExpect(jsonPath("$[1].empName").value("Yash Markad"))
					.andDo(print());
		verify(employeeService,times(1)).getAllEmployees();
	}
	
	@Test
	void addEmployee() throws JsonProcessingException, Exception {
		Employees newEmployees = new Employees(3,"Shubham Giram","shubham.giram@example.com","C",2024,"Testing Department");
		
		when(employeeService.addEmployee(any(Employees.class))).thenReturn(newEmployees);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newEmployees)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.empId").value(3))
				.andExpect(jsonPath("$.empName").value("Shubham Giram"))
				.andDo(print());
		
		verify(employeeService, times(1)).addEmployee(any(Employees.class));
	}
	@Test
	void updateEmployee() throws JsonProcessingException, Exception {
		Employees updatedEmployees = new Employees(3,"Sachin Shetkar","sachin.shetkar@example.com","A",2024,"Development Department");
		
		when(employeeService.updateEmployee(any(Employees.class))).thenReturn(updatedEmployees);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedEmployees)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.empId").value(3))
				.andExpect(jsonPath("$.empName").value("Sachin Shetkar"))
				.andDo(print());
		
		verify(employeeService, times(1)).updateEmployee(any(Employees.class));
	}
	@Test
	void deleteEmpById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/1"))
				.andExpect(status().isOk())
				.andDo(print());
		
		verify(employeeService,times(1)).deleteEmpById(1);
		
	}
}	
