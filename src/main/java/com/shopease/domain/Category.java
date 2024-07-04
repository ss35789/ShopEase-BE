package com.shopease.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryKey;

    @ManyToMany(mappedBy = "categories")
    private List<Item> items;

    @Column(nullable = false)
    private String name;
}
