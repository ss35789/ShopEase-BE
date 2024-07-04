package com.shopease.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartKey;

    @Column(nullable = false)
    private Long userKey;

    @Column(nullable = false)
    private Long itemKey;

    @Column(nullable = false)
    private Integer cnt;
}
