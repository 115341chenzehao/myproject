package com.czhhhh.demo.demos.springboot.mybatis.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.math.BigDecimal;

/**
 * 商品Bean对象 Goods
 */
@Data   //自动生成get、set、toString、构造器等
@Builder //用于链式编程
public class Goods {
    private Integer id;
    private BigDecimal price;
    private String name;
    private String description;

    /**
     * 由于 @Data和@Builder 配合使用的时候会导致无参构造方法丢失，所以我们主动声明了无参构造方法，并
     * 使用 @Tolerate 注解来告诉 lombok 请允许我们的无参构造方法存在（没有无参构造方法的时候会导致 ORM 映射出错）
     */
    @Tolerate
    public Goods(){
    }

}
