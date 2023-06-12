package com.example.simpleproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = ("foreigndebt"))
public class ForeignDebt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "foreigndebt_seq_id",sequenceName = "foreigndebt_seq_id",allocationSize = 1)

    @Column(name = ("foreigndebt_id"))
    private Integer foreignDebtId;
    private String companyName;
    private String fullName;
    private String firstPhoneNumber;
    private String secondPhoneNumber;
    private Integer productId;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;




}
