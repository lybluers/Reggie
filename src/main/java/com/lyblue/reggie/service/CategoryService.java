package com.lyblue.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyblue.reggie.entity.Category;
import com.lyblue.reggie.entity.Employee;

public interface CategoryService extends IService<Category> {

    public void remove(Long id);
}