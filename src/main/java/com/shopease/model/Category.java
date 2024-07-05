package com.shopease.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryKey;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "Category_Item",
            joinColumns = @JoinColumn(name = "categoryKey"),
            inverseJoinColumns = @JoinColumn(name = "itemKey")
    )
    private List<Item> items;
}
