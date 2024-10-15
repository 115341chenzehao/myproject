package com.czhhhh.demo.demos.springboot.mybatis.service;

import com.czhhhh.demo.demos.springboot.mybatis.entity.Goods;

import java.util.List;

public interface userGoodsService {
    //查询所有商品
    List<Goods> findAll();
    //根据id查询商品
    Goods findById(Integer id);
    //添加商品
    void addGoods(Goods goods);
    //根据id更新商品
    void updateGoods(Goods goods);
    //根据id删除商品
    void deleteById(Integer id);
}
