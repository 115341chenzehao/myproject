package com.czhhhh.demo.demos.springboot.mybatisplus.service;

import com.czhhhh.demo.demos.springboot.mybatisplus.entity.OrderMapper;
import com.czhhhh.demo.demos.springboot.mybatisplus.entity.Orders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Orders> findAll() {
        List<Orders> orders = orderMapper.selectList(null);
        log.info("查询所有订单信息："+orders.toString());
        return orders;
    }

    @Override
    public Orders findById(Integer orderId) {
        log.info("值为"+orderId);
        Orders orders = orderMapper.selectById(orderId);
        log.info("查询的订单为"+orders.toString());
        return orders;
    }


    @Override
    public void addOrder(Orders order) {
        orderMapper.insert(order);
        log.info("新增加的订单信息为"+order.toString());
    }

    @Override
    public void updateOrder(Orders order) {
        findById(order.getOrderId());
        orderMapper.updateById(order);
        log.info("更新后的订单信息为"+order);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("准备删除订单编号："+id);
        orderMapper.deleteById(id);
    }
}
