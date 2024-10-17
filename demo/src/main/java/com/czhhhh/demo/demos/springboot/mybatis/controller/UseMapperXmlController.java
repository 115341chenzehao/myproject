package com.czhhhh.demo.demos.springboot.mybatis.controller;

import com.czhhhh.demo.demos.springboot.mybatis.entity.Goods;
import com.czhhhh.demo.demos.springboot.mybatis.service.UseMapperXmlService;
import com.czhhhh.demo.demos.springboot.mybatis.service.UseMapperXmlServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 使用MyBatis的Mapper.xml功能 -- 适合复杂sql
 */
@RestController
@RequestMapping("/UseGoods")
@Slf4j
@Api("使用MyBatis的Mapper.xml功能")
public class UseMapperXmlController {

    @Autowired
    private UseMapperXmlServiceImpl useMapperXmlServiceImpl;

    @ApiOperation(value = "根据商品名称模糊查询商品，并按照价格排序",httpMethod = "GET")
    @RequestMapping("/searchGoodsByNameAndSortByPrice")
    public List<Goods> searchGoodsByNameAndSortByPrice(String name){
        List<Goods> goods = useMapperXmlServiceImpl.searchGoodsByNameAndSortByPrice(name);
        return goods;
    }

}
