package com.example.simpleproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForeignDebtDto {
    private Integer foreignDebtId;
    private String companyName;
    private String fullName;
    private String firstPhoneNumber;
    private String secondPhoneNumber;
    private Integer productsId;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
