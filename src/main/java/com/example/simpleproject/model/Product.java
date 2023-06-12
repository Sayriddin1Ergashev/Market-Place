package com.example.simpleproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("product"))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "product_seq_id",sequenceName = "product_seq_id",allocationSize = 1)

    private Integer productId;
    private String prodName;
    private Double receivedPrice;
    private Double sellingPrice;
    private Double prodMass;
    private Integer amount;
    private Integer productBaseId;
    private Integer imageId;
    private LocalDateTime expiredAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private Basket basket;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foreigndebt_id")
    private ForeignDebt foreignDebt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


  @ManyToOne
  @JoinColumn(referencedColumnName = "productbase_id")
  private ProductBase productBase;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Image image;
}
