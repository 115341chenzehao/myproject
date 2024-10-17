package com.czhhhh.demo.demos.springboot.mybatisplus.entity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Mapper接口继承BaseMapper，它自带CURD方法，就不需要自己写方法了
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders>{
}
