package com.czhhhh.demo.demos.springboot.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * MybatisPlus会自动对应表字段---java对象的属性，即使它们的命名有差异
 * @TableId 没有这个的话，MybatisPlus没法找到对应的主键
 */
@Data  //getset方法
@Builder //链式编程
public class Orders {
    @TableId //定义主键
    private Integer orderId;
    private String orderInfo;
    private Integer totalPrice;
    private Integer goodId;
    private Integer orderCount;

    @Tolerate //处理@Data和@Builder共用时，无参构造有问题的问题
    public Orders(){
    }
}
