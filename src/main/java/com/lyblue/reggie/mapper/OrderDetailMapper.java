package com.lyblue.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyblue.reggie.entity.OrderDetail;
import com.lyblue.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}