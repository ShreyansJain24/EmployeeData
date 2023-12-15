package com.myproj.EmployeeData.repositories;

import com.myproj.EmployeeData.entities.EmployeeDetails;
import com.myproj.EmployeeData.entities.OtpDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OtpRepository extends JpaRepository<OtpDetails,Long> {
    @Query("select o from OtpDetails o where o.mail= :mail")
    OtpDetails findByMail(String mail);

}
