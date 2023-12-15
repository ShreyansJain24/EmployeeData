package com.myproj.EmployeeData.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
	private EmployeeServiceImpl empoyeeServiceimpl;

	
	@PostMapping("/create")
	public ResponseEntity<EmployeeDetails> addBook(@RequestBody EmployeeDetails emp) {
		EmployeeDetails b=null;
		try {
			b=this.empoyeeServiceimpl.createEmployee(emp);
			System.out.println(emp);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	 @GetMapping("/joined-between")
	    public List<EmployeeDetails> getEmployeesJoinedBetween( @RequestParam("startDate")@DateTimeFormat(pattern = "yyyy-MM-dd")  Date startDate,
	            @RequestParam("endDate")@DateTimeFormat(pattern = "yyyy-MM-dd")  Date endDate) 
	      {
		 System.out.println("date");
	        return empoyeeServiceimpl.findByjoiningDateBetween(startDate, endDate);
	    }

}
