package com.example.simpleproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = ("employees"))
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mployees_seq_id",sequenceName = "mployees_seq_id",allocationSize = 1)
    @Column(name = "employees_id")
    private Integer employeesId;
    @Column(name = "created-at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "employeesId", cascade = CascadeType.ALL)
    private List<Users> users;

}
