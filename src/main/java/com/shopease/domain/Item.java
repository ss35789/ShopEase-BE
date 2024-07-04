package com.shopease.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "Item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemKey;

    @Column(nullable = false)
    private Long categoryKey;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Integer salePrice;
    private String content;
    private LocalDateTime uploadDate = LocalDateTime.now();

    private String img;

    public Item(){}

    public Item(String name, Integer price, Integer salePrice, String content) {
        this.name = name;
        this.price = price;
        this.salePrice = salePrice;
        this.content = content;
    }
}
