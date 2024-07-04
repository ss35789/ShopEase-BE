package com.shopease.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartKey;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_key")
    private Item item;

    @Column(nullable = false)
    private Integer quantity;
}
