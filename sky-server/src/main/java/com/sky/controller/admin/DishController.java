package com.sky.controller.admin;


import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.dto.EmployeeDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishItemVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品管理
 */
@Slf4j
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品管理")
public class DishController {
 @Autowired
 private DishService dishService;
    @ApiOperation("新增菜品")
    @PostMapping()
    public Result addDish(@RequestBody DishDTO dishDTO)
    {   log.info("新增菜品:{}",dishDTO);
        dishService.addDish(dishDTO);
        return Result.success();
    }

@GetMapping("/page")
@ApiOperation("分页查询菜品")
    public Result<PageResult> listDishByPage(DishPageQueryDTO dishPageQueryDTO)
    {
        log.info("分页查询菜品:",dishPageQueryDTO);
        PageResult pageResult=dishService.pageDishByPage(dishPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 根据id获取菜品信息
     * @param id
     * @return
     */
    @ApiOperation("根据id获取菜品信息")
    @GetMapping("/{id}")
    public  Result <DishVO> getDishById( @PathVariable Long id)
    {   log.info("根据id查询菜品:{}",id);
        DishVO dishVO=dishService.getDishVoById(id);
        return Result.success(dishVO);
    }

    @PutMapping
    @ApiOperation("编辑菜品信息")
    /**
     * 修改菜品信息
     */
    public  Result editDish(@RequestBody DishDTO dishDTO)
    {
        dishService.editDish(dishDTO);
        return  Result.success();
    }

    @DeleteMapping
    @ApiOperation("批量删除")
    /**
     * 批量删除
     */
    public  Result deleteDish(@RequestParam List<Long>  ids)
    {  log.info("菜品批量删除:{}",ids);
        dishService.deleteBatch(ids);
        return  Result.success();
    }


}
