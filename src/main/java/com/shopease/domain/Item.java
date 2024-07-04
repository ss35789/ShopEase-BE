package com.shopease.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "Item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemKey;

    @ManyToMany
    private List<Category> categories;

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
