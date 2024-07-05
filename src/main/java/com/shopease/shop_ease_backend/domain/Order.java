package com.shopease.shop_ease_backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderKey;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addrKey", nullable = false)
    private Address addr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userKey", nullable = false)
    private User user;

    @NotNull
    @Column(nullable = false)
    private Integer totalPrice;

    @Column
    private LocalDateTime buyDate = LocalDateTime.now();

    @NotNull
    @Column(nullable = false)
    private Integer orderItemsCnt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetailList;
}
