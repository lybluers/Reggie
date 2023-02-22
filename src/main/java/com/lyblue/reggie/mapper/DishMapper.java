package com.lyblue.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyblue.reggie.entity.Category;
import com.lyblue.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {


}