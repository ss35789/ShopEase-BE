package com.shopease.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userKey;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String tel;


    public User(){}

    public User(String userName, String email, String password, String tel) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.tel = tel;
    }
}
