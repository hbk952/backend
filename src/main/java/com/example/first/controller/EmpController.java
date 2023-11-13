package com.example.first.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.first.model.Employee;
import com.example.first.service.EmployeeService;

@RestController
@RequestMapping("/first")
public class EmpController {
	
	@Autowired
	private EmployeeService empServ;
	
	@GetMapping("/getAll")
	public List<Employee> getAllEmployee(){
//		System.out.println("inside getAllEmployee()");
		return empServ.getEmployees();
	}
	@GetMapping("/getById")
	public ResponseEntity<?> getById(@RequestParam("empId") Integer id){
		Employee emp =  empServ.getByEmpId(id);
		
		if(emp.getEmpID() == 0) {
			return new ResponseEntity<String>("Employee with the given ID not found",HttpStatus.BAD_REQUEST);
			
			
		}
		else{
			return new ResponseEntity<Employee>(emp,HttpStatus.FOUND);
		}
	}
	@GetMapping("/getByName")
	public ResponseEntity<?> getByName(@RequestParam("empName") String name){
		List<Employee> emp =  empServ.getByName(name);
		if(emp.size() == 0) {
			return new ResponseEntity<String>("Employee with the given name not found",HttpStatus.BAD_REQUEST);
			
			
		}
		else {
			return new ResponseEntity<List<Employee>>(emp,HttpStatus.FOUND);
		}
		
	}
	@GetMapping("/getBySalary")
	public ResponseEntity<?> getBySalary(@RequestParam("empSalary") Long empSalary){
		List<Employee> emp =  empServ.getBySalary(empSalary);
		if(emp.size() == 0) {
			return new ResponseEntity<String>("Employee with the given salary not found",HttpStatus.BAD_REQUEST);
			
			
		}
		else {
			return new ResponseEntity<List<Employee>>(emp,HttpStatus.FOUND);
		}
		
	}
	@GetMapping("/getByDept")
	public ResponseEntity<?> getByDept(@RequestParam("empDept") int empDept){
		List<Employee> emp =  empServ.getByDept(empDept);
		if(emp.size() == 0) {
			return new ResponseEntity<String>("Employee with the given department not found",HttpStatus.BAD_REQUEST);
			
			
		}
		else {
			return new ResponseEntity<List<Employee>>(emp,HttpStatus.FOUND);
		}
		
	}
	@PostMapping(value="/save", consumes="application/json ; charset=utf-8")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee emp){
		Employee isEmpExisting = empServ.getByEmpId(emp.getEmpID());
		System.out.println(isEmpExisting.toString());
		if(isEmpExisting.getEmpID()==0){
			Employee savedEmp = empServ.saveEmployee(emp);
			if(!savedEmp.equals(null)) {
				return new ResponseEntity<Employee>(savedEmp,HttpStatus.CREATED);
			
			}
			else {
				return new ResponseEntity<String>("Employee not saved",HttpStatus.BAD_REQUEST);
			}
			
		}
		else {
			return new ResponseEntity<String>("Employee already exist",HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping(value="/saveAll", consumes="application/json ; charset=utf-8")
	public ResponseEntity<?> saveAllEmployee(@RequestBody List<Employee> emp){
		
		if(emp.size() == 0) {
		
			return new ResponseEntity<String>("Empty List provided",HttpStatus.BAD_REQUEST);
		}
		else {
			List<Employee> listEmployee = empServ.saveAllEmployee(emp);
			if(listEmployee.size() != 0) {
				return new ResponseEntity<List<Employee>>(listEmployee,HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<String>("Unable to save all Emplyoee",HttpStatus.BAD_REQUEST);
			}
			
		}
	}
	@PutMapping("/updateEmployee")	
	public ResponseEntity<?> updateEmployee(@RequestBody Employee emp){
		Employee updatedEmp = empServ.updateEmployee(emp);
		if(!updatedEmp.equals(null)) {
			return new ResponseEntity<Employee>(updatedEmp,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<String>("Employee not updated",HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/deleteEmp")
	public ResponseEntity<?> deleteEmployee(@RequestParam("empId") Integer id){
		Employee isEmpExisting = empServ.getByEmpId(id);
		if(isEmpExisting.getEmpID() ==0) {
			return new ResponseEntity<String>("Employee doesn't exist",HttpStatus.BAD_REQUEST);
			
		}
		else {
			boolean result = empServ.deleteEmployeeById(id);
			System.out.println("Employee deleted "+result);
			if(result) {
				return new ResponseEntity<String>("Employee deleted succesfully",HttpStatus.GONE);
			}
			return new ResponseEntity<String>("Employee not deleted",HttpStatus.BAD_REQUEST);
		}
		
	}
}
