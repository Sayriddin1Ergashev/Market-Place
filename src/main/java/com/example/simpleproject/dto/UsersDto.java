package com.example.simpleproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersDto {
    private Integer usersId;
    @NotBlank(message = "firstname cannot be null or empty ")
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
    private LocalDateTime brithDate;
    private LocalDateTime workingTime;
    private LocalDateTime workingdays;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    private Integer employeesId;
  //  private EmployeesDto employeesDto;

    private Integer imageId;
    private ImageDto image;


}
