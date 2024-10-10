package com.czhhhh.demo.demos.springboot.jpa;

import java.util.List;
import java.util.Optional;

/**
 * User服务接口
 * 查、增、改、删
 */
public interface UserService {
    User findById(Integer id); //根据id查sql
    List<User> findall(); //select * from table
    User addUser(User user); // insert
    User updateUser(User user); // update
    void delete(Integer id); // delete
}
