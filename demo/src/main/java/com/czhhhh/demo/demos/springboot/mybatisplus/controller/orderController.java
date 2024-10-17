package com.czhhhh.demo.demos.springboot.mybatisplus.controller;

import com.czhhhh.demo.demos.springboot.mybatisplus.entity.Orders;
import com.czhhhh.demo.demos.springboot.mybatisplus.service.OrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("MybatisPlus的测试")
@RestController
@Slf4j
@RequestMapping("/order")
public class orderController {

    @Autowired
    private OrderServiceImpl orderService;

    @ApiOperation(value = "查询所有订单信息",httpMethod = "GET")
    @RequestMapping("/findAll")
    public List<Orders> findAll(){
        return orderService.findAll();
    }

    @ApiOperation(value = "根据订单id查询所有订单信息",httpMethod = "GET")
    @RequestMapping("/findById")
    public Orders findById(Integer orderId) {
       return orderService.findById(orderId);
    }


    @ApiOperation(value = "增加订单",httpMethod = "POST")
    @RequestMapping("/addOrder")
    public void addOrder(Orders order) {
        orderService.addOrder(order);
    }

    @ApiOperation(value = "更新订单",httpMethod = "PUT")
    @RequestMapping("/updateOrder")
    public void updateOrder(Orders order) {
        orderService.updateOrder(order);
    }

    @ApiOperation(value = "删除订单",httpMethod = "DELETE")
    @RequestMapping("/deleteById")
    public void deleteById(Integer id) {
        orderService.deleteById(id);
    }

}
