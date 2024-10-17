package com.czhhhh.demo.demos.springboot.mybatisplus.service;

import com.czhhhh.demo.demos.springboot.mybatisplus.entity.Orders;

import java.util.List;

public interface OrderService {
    public List<Orders> findAll(); //查询所有订单

    Orders findById(Integer orderId); //根据id查询订单
    public void addOrder(Orders order); //增加订单
    public void updateOrder(Orders order); //修改订单
    public void deleteById(Integer id); //根据id删除订单
}
