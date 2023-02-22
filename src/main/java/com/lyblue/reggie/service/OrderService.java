package com.lyblue.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyblue.reggie.entity.Orders;
import com.lyblue.reggie.entity.User;

public interface OrderService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    public void  submit(Orders orders);
}
