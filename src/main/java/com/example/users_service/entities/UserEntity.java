package com.example.users_service.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Accessors(chain = true)
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;
}
