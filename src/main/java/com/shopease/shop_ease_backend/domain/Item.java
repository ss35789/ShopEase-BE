package com.shopease.shop_ease_backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemKey;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Integer price;

    @NotNull
    @Column(nullable = false)
    private Integer salePrice;

    private String content;

    @Column(nullable = false)
    private LocalDateTime uploadDate = LocalDateTime.now();

    private String img;

    public Item() {}

    public Item(String name, Integer price, Integer salePrice, String content) {
        this.name = name;
        this.price = price;
        this.salePrice = salePrice;
        this.content = content;
    }
}
