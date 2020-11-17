package com.guli.teacher.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.result.Result;
import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.entity.query.TeacherQuery;
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
    @GetMapping("list")
    public Result list() {
        try {
            List<EduTeacher> list = teacherService.list(null);
            return Result.ok().data("items", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    //删除
    @ApiOperation(value = "讲师删除")
    @DeleteMapping("/{id}")//占位符:
    //1.如果占位符中的参数名和形参名一致的话那么@PathVariable可以省略
    //2.如果配置了Swagger、并在形参前加了其他注解，那么@PathVariable必须加;
    public Result deleteTeacherById(
            @ApiParam(name = "id", value = "讲师Id", required = true)
            @PathVariable String id) {
        try {
            teacherService.removeById(id);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    @ApiOperation(value = "讲师分页列表")
    @GetMapping("/{page}/{limit}")
    public Result selectTeacherByPage(
            @ApiParam(name = "page", value = "当前页", required = true)
            @PathVariable(value = "page") Integer page,
            @ApiParam(name = "limit", value = "每页显示记录数", required = true)
            @PathVariable(value = "limit") Integer limit) {
        try {
            Page<EduTeacher> teacherPage = new Page<EduTeacher>(page, limit);
            teacherService.page(teacherPage, null);
            return Result.ok().data("total", teacherPage.getTotal()).data("rows", teacherPage.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    //条件查询的分页
    //1.有分页
    //2.有条件：根据讲师名称，讲师等级，创建时间，修改时间
    @ApiOperation(value = "根据讲师条件分页查询")
    @PostMapping("/{page}/{limit}")
    public Result selectTeacherByPageWrapper(
            @ApiParam(name = "page", value = "当前页", required = true)
            @PathVariable(value = "page") Integer page,
            @ApiParam(name = "limit", value = "每页显示记录数", required = true)
            @PathVariable(value = "limit") Integer limit,
            @RequestBody TeacherQuery query) {

        try {
            Page<EduTeacher> teacherPage = new Page<EduTeacher>(page, limit);
            teacherService.pageQuery(teacherPage, query);
            return Result.ok().data("total", teacherPage.getTotal()).data("rows", teacherPage.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }
    
    @ApiOperation(value = "保存讲师对象")
    @PostMapping("save")
    public Result saveTeacher(@RequestBody EduTeacher teacher){
        try {
            teacherService.save(teacher);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
    
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/{id}")
    public Result saveTeacherById(
            @ApiParam(name = "id", value = "讲师Id", required = true)
            @PathVariable String id) {
        try {
            EduTeacher teacher = teacherService.getById(id);
            return Result.ok().data("teacher", teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }


    @ApiOperation(value = "修改讲师信息")
    @PutMapping("update")
    public Result selectTeacherById(@RequestBody EduTeacher teacher){
        try {
            teacherService.updateById(teacher);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

}
