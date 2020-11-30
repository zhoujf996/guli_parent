package com.guli.teacher.mapper;

import com.guli.teacher.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.teacher.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author guli
 * @since 2020-11-26
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     * 根据课程ID查询发布课程的详情 方法一：vo实现
     */
    CoursePublishVo getCoursePublishVoById(String id);
    
    /**
     * 根据课程ID查询发布课程的详情 方法二：map实现
     */
    Map<String, Object> getMapById(String id);
}
