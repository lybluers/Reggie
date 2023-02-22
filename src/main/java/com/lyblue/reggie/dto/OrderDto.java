package com.lyblue.reggie.dto;

import com.lyblue.reggie.entity.OrderDetail;
import com.lyblue.reggie.entity.Orders;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto extends Orders {

    private List<OrderDetail> orderDetails;
}