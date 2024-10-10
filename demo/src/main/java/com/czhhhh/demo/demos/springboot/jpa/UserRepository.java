package com.czhhhh.demo.demos.springboot.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA接口，传递实体类、主键类型
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
