package com.czhhhh.demo.demos.springboot.jpa;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    // JPA接口，用于数据处理规范，可以调用它的内置方法，进行数据交互
    @Resource
    private UserRepository userRepository;


    @Override
    public User findById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> findall() {
        return userRepository.findAll();
    }

    /**
     * JPA接口中，增和改都是用save方法
     * @param user
     * @return
     */
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
