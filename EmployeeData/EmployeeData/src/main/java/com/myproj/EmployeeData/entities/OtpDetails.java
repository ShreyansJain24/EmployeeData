package com.myproj.EmployeeData.entities;

import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OtpDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String mail;
    private String otp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date expiryTime;
}
