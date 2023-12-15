package com.myproj.EmployeeData.controller;

import java.util.*;

import com.myproj.EmployeeData.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproj.EmployeeData.entities.EmployeeDetails;
import com.myproj.EmployeeData.service.impl.EmployeeServiceImpl;



@RestController
@RequestMapping("/emp")
public class EmployeeController {
	@Autowired
	private EmployeeService empoyeeService;

	
	@PostMapping("/create")
	public ResponseEntity<String> addBook(@RequestBody EmployeeDetails emp) {
		try {
			empoyeeService.createEmployee(emp);
			return ResponseEntity.ok("Employee Created Succesfuly");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	 @GetMapping("/joined-between")
	    public List<EmployeeDetails> getEmployeesJoinedBetween( @RequestParam("startDate")@DateTimeFormat(pattern = "yyyy-MM-dd")  Date startDate,
	            @RequestParam("endDate")@DateTimeFormat(pattern = "yyyy-MM-dd")  Date endDate) 
	    {
			List<EmployeeDetails> empList=null;
			try{
                 empList=empoyeeService.findByjoiningDateBetween(startDate, endDate);
				 if(Objects.nonNull(empList)){
					 return empList;
				 }
				 else{
					 System.out.println("no data found for employee");
					 return empList;
				 }
			}
			catch(Exception e){
				System.out.println("error occured while fetching data");
				e.printStackTrace();
			}

	        return empList;
	    }

}
