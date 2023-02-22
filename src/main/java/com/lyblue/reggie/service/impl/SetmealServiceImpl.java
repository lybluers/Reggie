package com.lyblue.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyblue.reggie.common.CustomException;
import com.lyblue.reggie.dto.SetmealDto;
import com.lyblue.reggie.entity.Setmeal;
import com.lyblue.reggie.entity.SetmealDish;
import com.lyblue.reggie.mapper.SetmealMapper;
import com.lyblue.reggie.service.SetmealDIshService;
import com.lyblue.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDIshService setmealDIshService;

    /**
     * 新增套餐，同时保存套餐和菜品的关联关系
     * @param setmealDto
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        // 保存套餐基本信息，操作setmeal表，执行insert操作
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        // 保存套餐和菜品的关联信息，操作setmeal_dish
        setmealDIshService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */

    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {

        // 查询套餐状态，确定可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);
        long count = this.count(queryWrapper);

        if(count > 0){
            // 如果不能删除，抛出一个业务异常
            throw new CustomException("套餐正在售卖中,无法删除");
        }

        // 如果可以删除，先删除套餐表中的数据 -- setmeal
        this.removeByIds(ids);


        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);

        // 删除关系表中的数据 -- setmeal_dish
        setmealDIshService.remove(lambdaQueryWrapper);

    }
}