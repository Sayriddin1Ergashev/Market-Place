package com.example.simpleproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductBaseDto {
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
