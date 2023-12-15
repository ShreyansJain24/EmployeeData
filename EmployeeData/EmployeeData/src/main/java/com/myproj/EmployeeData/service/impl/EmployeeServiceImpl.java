package com.myproj.EmployeeData.service.impl;

import java.util.Date;
import java.util.List;

import com.myproj.EmployeeData.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproj.EmployeeData.entities.EmployeeDetails;
import com.myproj.EmployeeData.repositories.EmployeeRepository;



@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	

	@Override
	public EmployeeDetails createEmployee(EmployeeDetails employee) {
		EmployeeDetails emp=this.employeeRepository.save(employee);
		return emp;
	}

	@Override
	public EmployeeDetails findUserByMail(String mail) {
		return employeeRepository.findEmployeeByMail(mail);
	}

	@Override
	public List<EmployeeDetails> findByjoiningDateBetween(Date startDate, Date endDate) {
        return employeeRepository.findByjoiningDateBetween(startDate, endDate);
    }

}
