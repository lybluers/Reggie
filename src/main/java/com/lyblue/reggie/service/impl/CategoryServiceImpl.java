package com.lyblue.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyblue.reggie.common.CustomException;
import com.lyblue.reggie.entity.Category;
import com.lyblue.reggie.entity.Dish;
import com.lyblue.reggie.entity.Setmeal;
import com.lyblue.reggie.mapper.CategoryMapper;
import com.lyblue.reggie.service.CategoryService;
import com.lyblue.reggie.service.DishService;
import com.lyblue.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;
    /**
     * 根据id删除分类，删除之前需要进行判断
     * @param id
     */

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 添加查询条件，根据分类id进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        long count = dishService.count(dishLambdaQueryWrapper);
        // 查询当前分类是否关联了某个菜品，如果已经关联，抛出异常
        if(count > 0){
            // 已经关联菜品，抛出异常
            throw new CustomException("当前分类项关联菜品，无法删除");
        }


        // 查询当前分类是否关联了某个套餐，如果已经关联，抛出异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        long count1 = setmealService.count(setmealLambdaQueryWrapper);
        if(count1 > 0){
            throw new CustomException("当前分类项关联套餐，无法删除");
        }

        // 删除分类
        super.removeById(id);
    }
}