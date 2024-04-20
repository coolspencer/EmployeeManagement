package com.example.EmployeeManagement.EmployeeManagement.model;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="Employee Dta")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name ="firstName", nullable = false)
    private String firstName;

    @Column(name="lastName")
    private String lastname;

    @Column(name="emailId")
    private String emailId;


}

























