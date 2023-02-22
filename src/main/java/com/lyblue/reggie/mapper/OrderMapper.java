package com.lyblue.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyblue.reggie.entity.AddressBook;
import com.lyblue.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}