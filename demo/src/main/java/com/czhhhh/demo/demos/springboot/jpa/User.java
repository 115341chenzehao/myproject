package com.czhhhh.demo.demos.springboot.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private Integer id;
    private String name;
    private String password;
    private Integer age;

    public User() {
    }

    public User(Integer id, String name, String password, Integer age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
    }
}