package com.czhhhh.demo.demos.springboot.mybatis.entity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 使用resource/GoodsXmlMapper.xml 方式的Mybatis --适合复杂Sql
 */
@Mapper
public interface GoodsXmlMapper{
    //    根据商品名称模糊查询商品，并按照价格排序
    List<Goods> searchGoodsByNameAndSortByPrice(String name);
}
