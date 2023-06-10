package com.example.simpleproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("image"))
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(sequenceName = "image_seq_id", name = "image_seq_id", allocationSize = 1)
    @Column(name = ("image_id"))
    private Integer imageId;
    private Integer usersId;
    private Integer productId;
    private String path;
    private String type;
    private Integer size;
    private String token;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
