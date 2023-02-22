package com.lyblue.reggie.dto;

import com.lyblue.reggie.entity.Setmeal;
import com.lyblue.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
