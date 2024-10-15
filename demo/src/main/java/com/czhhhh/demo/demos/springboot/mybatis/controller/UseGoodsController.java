package com.czhhhh.demo.demos.springboot.mybatis.controller;

import com.czhhhh.demo.demos.springboot.mybatis.entity.Goods;
import com.czhhhh.demo.demos.springboot.mybatis.service.UseGoodsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/UseGoods")
@Slf4j
@Api("使用Mybatis进行增删改查")
public class UseGoodsController {

    @Autowired
    private UseGoodsServiceImpl useGoodsServiceImpl;

    @ApiOperation(value = "查询所有商品",httpMethod = "GET")
    @RequestMapping("/findAll")
    public List<Goods> finaALl(){
        List<Goods> all = useGoodsServiceImpl.findAll();
        log.info("查询所有商品:"+all.toString());
        return all;
    }

    @ApiOperation(value = "根据id查询商品",httpMethod = "GET")
    @RequestMapping("/findById")
    public Goods findById(Integer id){
        Goods goods = useGoodsServiceImpl.findById(id);
        log.info("查询商品："+goods.toString());
        return goods;
    }

    @ApiOperation(value = "增加商品",httpMethod = "POST")
    @RequestMapping("/addGoods")
    public void addGoods(Goods goods){
        useGoodsServiceImpl.addGoods(goods);
        log.info("添加商品："+goods.toString());
    }

    @ApiOperation(value = "删除商品",httpMethod = "DELETE")
    @RequestMapping("/deleteById")
    public void deleteById(Integer id){
        Goods goods = findById(id);
        log.info("准备删除商品："+goods.toString());
        useGoodsServiceImpl.deleteById(id);
    }

    @ApiOperation(value = "根据id修改商品",httpMethod = "PUT")
    @RequestMapping("/updateGoods")
    public void updateGoods(Goods goods){
        useGoodsServiceImpl.updateGoods(goods);
        log.info("修改商品："+goods.toString());
    }


}
