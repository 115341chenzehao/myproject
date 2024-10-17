package com.czhhhh.demo.demos.springboot.mybatis.service;

import com.czhhhh.demo.demos.springboot.mybatis.entity.Goods;
import com.czhhhh.demo.demos.springboot.mybatis.entity.GoodsXmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UseMapperXmlServiceImpl implements UseMapperXmlService{

    @Autowired
    private GoodsXmlMapper goodsXmlMapper;

    @Override
    public List<Goods> searchGoodsByNameAndSortByPrice(String name) {
        log.info("=======通过Mybatis的Xml方式进行查询======");
        List<Goods> goodsByName = goodsXmlMapper.searchGoodsByNameAndSortByPrice(name);
        log.info("=======复杂sql，根据名称模糊查询并按价格排序："+goodsByName.toString());
        return goodsByName;
    }
}
