package com.czhhhh.demo.demos.springboot.mybatis.service;

import com.czhhhh.demo.demos.springboot.mybatis.entity.Goods;
import com.czhhhh.demo.demos.springboot.mybatis.entity.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseGoodsServiceImpl implements userGoodsService{

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> findAll() {
        return goodsMapper.findAll();
    }

    @Override
    public Goods findById(Integer id) {
        return goodsMapper.findById(id);
    }

    @Override
    public void addGoods(Goods goods) {
        goodsMapper.addGoods(goods);
    }

    @Override
    public void updateGoods(Goods goods) {
        goodsMapper.updateGoods(goods);
    }

    @Override
    public void deleteById(Integer id) {
        goodsMapper.deleteById(id);
    }
}
