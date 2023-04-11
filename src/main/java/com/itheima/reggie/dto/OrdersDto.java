package com.itheima.reggie.dto;

import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.DishFlavor;
import com.itheima.reggie.entity.Orders;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrdersDto extends Orders {

    private Integer SumNum;

}
