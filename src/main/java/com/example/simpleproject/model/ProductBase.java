package com.example.simpleproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = ("productbase"))
public class ProductBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "productbase_seq_id",sequenceName = "productbase_seq_id",allocationSize = 1)

    @Column(name = ("productbase_id"))
    private Integer productBaseId;
    private String prodName;
    private Double receivedPrice;
    private Double sellingPrice;
    private Double prodMass;
    private Integer amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;




}
