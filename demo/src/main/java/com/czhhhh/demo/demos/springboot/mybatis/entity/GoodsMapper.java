package com.czhhhh.demo.demos.springboot.mybatis.entity;


import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //让MapperScan可以自动扫描到
public interface GoodsMapper {

    @Select("select * from goods") //查询商品表所有内容
    List<Goods> findAll();

    @Select("select * from goods where id = #{id}") //根据id查询商品
    Goods findById(Integer id);

    @Insert("insert into goods value(#{id},#{name},#{price},#{description})") //添加商品
    void addGoods(Goods goods);

    @Update("update goods set name=#{name},price=#{price},description=#{description} where id=#{id}") //根据id更新商品
    void updateGoods(Goods goods);

    @Delete("delete from goods where id=#{id}") //根据id删除商品
    Boolean deleteById(Integer id);
}
