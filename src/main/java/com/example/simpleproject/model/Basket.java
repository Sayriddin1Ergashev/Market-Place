package com.example.simpleproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = ("basket"))
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "basket_seq_id",sequenceName = "basket_seq_id",allocationSize = 1)
    private Integer basketId;
    private Integer productId;
    private Double prodMass;
    private Double prodPrice;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loaner_id")
    private Loaner loaner;

}
