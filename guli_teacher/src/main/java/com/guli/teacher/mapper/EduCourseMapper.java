package com.guli.teacher.mapper;

import com.guli.teacher.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.teacher.entity.vo.CoursePublishVo;

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
     * 
     * @param id
     * @return
     */
    CoursePublishVo getCoursePublishVoById(String id);
}
