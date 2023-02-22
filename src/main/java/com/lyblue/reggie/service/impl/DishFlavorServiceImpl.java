package com.lyblue.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyblue.reggie.entity.DishFlavor;
import com.lyblue.reggie.mapper.DishFlavorMapper;
import com.lyblue.reggie.service.DishFlavorService;
import com.lyblue.reggie.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}