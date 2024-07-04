package com.shopease.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartKey;

    @ManyToOne
    private User user;

    @OneToOne
    private Item item;

    @Column(nullable = false)
    private Integer quantity;
}
