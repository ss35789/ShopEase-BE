package com.shopease.shop_ease_backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addrKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userKey", nullable = false)
    private User user;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String address;

    @NotNull
    @Column(nullable = false)
    private String tel;

    @NotNull
    @Column
    private String req = "조심히 와주세요";

    public Address(){}
}
