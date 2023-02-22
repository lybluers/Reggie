package com.lyblue.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyblue.reggie.entity.OrderDetail;
import com.lyblue.reggie.entity.Orders;
import com.lyblue.reggie.mapper.OrderDetailMapper;
import com.lyblue.reggie.mapper.OrderMapper;
import com.lyblue.reggie.service.OrderDetailService;
import com.lyblue.reggie.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}