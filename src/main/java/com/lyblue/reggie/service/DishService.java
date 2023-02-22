package com.lyblue.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyblue.reggie.dto.DishDto;
import com.lyblue.reggie.entity.Category;
import com.lyblue.reggie.entity.Dish;

public interface DishService extends IService<Dish> {

    // 新增数据，同时插入菜品对应的口味数据，需要操作两张表：dish，dishFlavor
    public void saveWithFlavor(DishDto dishDto);

    // 根据菜品id查询对应的口味信息
    public DishDto getByIdWithFlavor(Long id);

    // 更新菜品信息，同时更新口味信息
    public void updateWithFlavor(DishDto dishDto);
}