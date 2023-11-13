package com.example.first.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.first.model.Employee;
import com.example.first.repository.EmpRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
//	@Autowired
//	private SessionFactory sessionFac;
	@Autowired
	private EmpRepository empRepo;
//	@PostConstruct
//	public void initDB() {
//		List<Employee> emp = new ArrayList();
//		emp.add(new Employee(111,"Harish","SE",300,100000));
//		emp.add(new Employee(112,"Sanchit","SE",100,100000));
//		emp.add(new Employee(113,"Amit","ASE",020,100000));
//		empRepo.saveAll(emp);
//	}
	
	public List<Employee> getEmployees(){
		System.out.println("inside getEmployees()");
		return empRepo.findAll();
	}
	public List<Employee> getByName(String name){
		return empRepo.findByempName(name);
		
	}
	
	public List<Employee> getBySalary(Long salary){
		return empRepo.findByempSalaryGreaterThanEqual(salary);
		
	}
	public List<Employee> getByDept(int empDept){
		return empRepo.findByempDept(empDept);
		
	}
	
	public Employee saveEmployee(Employee emp) {
		return empRepo.saveAndFlush(emp);
	}
	public List<Employee> saveAllEmployee(List<Employee> emp) {
		return empRepo.saveAllAndFlush(emp);
	}
	
	public Employee getByEmpId(int id){
		
		Employee emp = empRepo.findById(id).orElse(new Employee());
		return emp;
	}
	
	public boolean deleteEmployeeById(int id) {
		Employee existing = getByEmpId(id);
		if(!existing.equals(null)) {
			return false;
		}
		else {
			empRepo.deleteById(id);
			return true;
		}
		
	}
	
	
	public Employee updateEmployee(Employee emp) {
		Employee existing = getByEmpId(emp.getEmpID());
		existing.setEmpDept(emp.getEmpDept());
		existing.setEmpDesignation(emp.getEmpDesignation());
		existing.setEmpID(emp.getEmpID());
		existing.setEmpName(emp.getEmpName());
		existing.setEmpSalary(emp.getEmpSalary());
		return empRepo.save(existing);
	}
}
