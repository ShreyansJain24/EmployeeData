package com.myproj.EmployeeData.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myproj.EmployeeData.entities.EmployeeDetails;



@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails, Integer> {
	 List<EmployeeDetails> findByjoiningDateBetween(Date startDate, Date endDate);

	 @Query("select e from EmployeeDetails e where e.email= :mail")
	 EmployeeDetails findEmployeeByMail(String mail);

}
