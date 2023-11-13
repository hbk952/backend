package com.example.first.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.first.model.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findByempName(String empName);
	List<Employee> findByempSalaryGreaterThanEqual(Long empSalary);
	List<Employee> findByempDept(int empDept);
//	Employee findByempId(int empId);
}
