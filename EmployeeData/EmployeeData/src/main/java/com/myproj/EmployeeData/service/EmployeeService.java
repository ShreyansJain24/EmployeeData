package com.myproj.EmployeeData.service;

import com.myproj.EmployeeData.entities.EmployeeDetails;

import java.util.Date;
import java.util.List;

public interface EmployeeService {
	EmployeeDetails createEmployee(EmployeeDetails employee);

	EmployeeDetails findUserByMail(String mail);

	List<EmployeeDetails> findByjoiningDateBetween(Date startDate, Date endDate);

}
