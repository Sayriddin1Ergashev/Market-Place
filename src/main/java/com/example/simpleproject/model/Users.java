package com.example.simpleproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.Arrays;

@Getter
@Setter
@Entity
@Table(name = ("users"))
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usersId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String userName;
    private String borrowName;
    private String phoneNumber;
    private String passportSerial;
    private String firstAddress;
    private String secondAddress;
    private Integer monthlyPrice;


   private Integer employeesId;


    private LocalDateTime brithDate;
    private LocalDateTime workingTime;
    private LocalDateTime workingdays;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usersId", referencedColumnName = "usersId",insertable = false,updatable = false)
    private Image image;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usersId", referencedColumnName = "usersId",insertable = false,updatable = false)
    private Loaner loaner;


}
