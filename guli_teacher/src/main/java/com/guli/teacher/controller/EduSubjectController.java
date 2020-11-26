package com.guli.teacher.controller;


import com.guli.common.result.Result;
import com.guli.teacher.entity.EduSubject;
import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.entity.vo.OneSubject;
import com.guli.teacher.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author guli
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("import")
    public Result importSubject(MultipartFile file) {

        //因为考虑到Excel模板中数据不准确，所有返回多个错误信息，那么多个错误信息放在集合中
        List<String> mesList = subjectService.importExcel(file);

        if (mesList.size() == 0) {
            return Result.ok();
        } else {
            return Result.ok().data("messageList", mesList);
        }
    }

    /**
     * 获取课程分类的Tree
     * @return
     */
    @GetMapping("tree")
    public Result TreeSubject() {
        List<OneSubject> subjectList = subjectService.getTree();
        return Result.ok().data("subjectList", subjectList);
    }


    /**
     * 可以查一下什么时候用@PathVariable
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        boolean b = subjectService.deleteById(id);
        if(b){
            return Result.ok();
        }else{
            return Result.error();
        }
    }
}

