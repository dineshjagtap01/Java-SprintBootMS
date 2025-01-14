package com.empapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empapplication.entities.Employees;

@Repository
public interface EmployeeRepo extends JpaRepository<Employees,Integer> {

	Employees findByEmpId(int id);
}