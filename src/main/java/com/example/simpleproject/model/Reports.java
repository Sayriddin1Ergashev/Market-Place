package com.example.simpleproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("reports"))
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "reports_seq_id",sequenceName = "reports_seq_id",allocationSize = 1)

    private Integer reportsId;
    private Integer categoryId;
    private String prodName;
    private Double prodPresent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


}
