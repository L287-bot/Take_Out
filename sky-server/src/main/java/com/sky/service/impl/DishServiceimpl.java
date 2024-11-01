package com.sky.service.impl;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DishServiceimpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    /**
     * 新增菜品和对应口味
     * @param dishDTO
     */
    @Override
    @Transactional
    public void addDish(DishDTO dishDTO) {
        //向菜品表插入数据
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.addDish(dish);
        //获取菜品id 插入数据后对id属性赋值
        Long dishId=dish.getId();
        //向口味表插入多条数据
        List<DishFlavor> dishFlavorList =dishDTO.getFlavors();
        for (DishFlavor flavor: dishFlavorList) {
            flavor.setDishId(dishId);
        }
        if (dishFlavorList!=null&&dishFlavorList.size()>0)
        {
          dishFlavorMapper.insertBatch(dishFlavorList);

        }
    }
}
