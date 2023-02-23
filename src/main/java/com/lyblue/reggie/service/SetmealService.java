package com.lyblue.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyblue.reggie.dto.SetmealDto;
import com.lyblue.reggie.entity.Category;
import com.lyblue.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐，同时保存套餐和菜品的关联关系
     * @param setmealDto
     */

    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */

    public void removeWithDish(List<Long> ids);

    /**
     * 根据套餐id修改售卖状态
     * @param status
     * @param ids
     */
    public void updateSetmealStatusById(Integer status,List<Long> ids);

    /**
     * 回显套餐数据：根据套餐id查询套餐
     * @return
     */
    public SetmealDto getDate(Long id);
}