package com.shopease.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "orderKey", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "itemKey", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Integer cnt;

    @Column(nullable = false)
    private Integer priceAt;
}
