package com.guli.teacher.controller;


import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author guli
 * @since 2020-11-16
 */
@RestController
@RequestMapping("/teacher")
@Api(description = "讲师管理")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/list")
    public List<EduTeacher> list() {
        return teacherService.list(null);
    }

    //删除
    @ApiOperation(value = "讲师删除")
    @DeleteMapping("/{id}")//占位符:
    //1.如果占位符中的参数名和形参名一致的话那么@PathVariable可以省略
    //2.如果配置了Swagger、并在形参前加了其他注解，那么@PathVariable必须加;
    public boolean deleteTeacherById(
            @ApiParam(name = "id", value = "讲师Id", required = true) 
            @PathVariable String id) {
        try {
            teacherService.removeById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}