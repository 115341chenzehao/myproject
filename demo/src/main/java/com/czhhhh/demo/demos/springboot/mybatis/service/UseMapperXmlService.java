package com.czhhhh.demo.demos.springboot.mybatis.service;

import com.czhhhh.demo.demos.springboot.mybatis.entity.Goods;

import java.util.List;

/**
 * 用Mybatis的Mapper.xml功能
 */
public interface UseMapperXmlService {
//    根据商品名称模糊查询商品，并按照价格排序
    List<Goods> searchGoodsByNameAndSortByPrice(String name);
}
