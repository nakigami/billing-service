package com.soa.glsid.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class ProductItem {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Long ProductId;
    @Transient
    private Product product;
    private double price;
    private double quantity;
    @ManyToOne
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Bill bill;
}
