package com.example.simpleproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("loaner"))
public class Loaner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "loaner_seq_id",sequenceName = "loaner_seq_id",allocationSize = 1)

    @Column(name = ("loaner_id"))
    private Integer loanerId;
    private Double totalPrice;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
