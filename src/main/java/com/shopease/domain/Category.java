package com.shopease.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    private List<Item> itemList;
}
