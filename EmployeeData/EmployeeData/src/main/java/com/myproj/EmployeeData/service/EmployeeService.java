package com.myproj.EmployeeData.service;

import com.myproj.EmployeeData.entities.EmployeeDetails;

public interface EmployeeService {
	EmployeeDetails createEmployee(EmployeeDetails employee);

	EmployeeDetails findUserByMail(String mail);

}
