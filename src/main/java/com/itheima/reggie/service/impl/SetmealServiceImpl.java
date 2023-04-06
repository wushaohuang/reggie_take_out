package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.entity.SetmealDish;
import com.itheima.reggie.mapper.SetmealMapper;
import com.itheima.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Service
    public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService{

        @Autowired
        private SetmealDishService setmealDishService;

        //新增套餐，同时要保持与菜品的关联关系
        @Override
        @Transactional
        public void saveWithDish(SetmealDto setmealDto) {
            //保存套餐基本信息，操作setmeal，执行insert操作
            this.save(setmealDto);

            List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();

            setmealDishes.stream().map((item)->{
                item.setSetmealId(setmealDto.getId());
                return item;
            }).collect(Collectors.toList());

            //保存套餐和菜品的关联信息，操作setmeal_dish，执行insert操作
            setmealDishService.saveBatch(setmealDishes);

        }
    }
}
