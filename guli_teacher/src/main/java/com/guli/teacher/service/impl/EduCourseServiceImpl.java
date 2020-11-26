package com.guli.teacher.service.impl;

import com.guli.teacher.entity.EduCourse;
import com.guli.teacher.entity.EduCourseDescription;
import com.guli.teacher.entity.vo.CourseVo;
import com.guli.teacher.mapper.EduCourseMapper;
import com.guli.teacher.service.EduCourseDescriptionService;
import com.guli.teacher.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author guli
 * @since 2020-11-26
 */
@Service
@Transactional
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    
    
    @Override
    public String saveVo(CourseVo vo) {
        
        //1、添加课程
        baseMapper.insert(vo.getCourse());
        //2、获取课程ID
         String courseId = vo.getCourse().getId();
        //3.添加课程描述
        vo.getEduCourseDescription().setId(courseId);
        courseDescriptionService.save(vo.getEduCourseDescription());
       
        return courseId;
    }
}
