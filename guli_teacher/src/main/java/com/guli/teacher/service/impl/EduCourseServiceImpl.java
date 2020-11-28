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
        baseMapper.insert(vo.getEduCourse());
        //2、获取课程ID
        String courseId = vo.getEduCourse().getId();
        //3、添加课程描述
        vo.getCourseDescription().setId(courseId);
        courseDescriptionService.save(vo.getCourseDescription());

        return courseId;
    }

    /**
     * 根据课程ID查询课程基本信息和描述
     *
     * @param id
     * @return
     */
    @Override
    public CourseVo getCourseVoById(String id) {
        //创建一个vo对象
        CourseVo vo = new CourseVo();
        //根据课程ID获取课程对象 EduCourse
        EduCourse eduCourse = baseMapper.selectById(id);
        if (eduCourse == null) {
            return vo;
        }
        //把课程加到vo对象中
        vo.setEduCourse(eduCourse);
        //根据课程ID获取课程的描述
        EduCourseDescription eduCourseDescription = courseDescriptionService.getById(id);
        //根据课程描述加到vo对象中
        if (eduCourseDescription == null) {
            return vo;
        }
        vo.setCourseDescription(eduCourseDescription);
        return vo;
    }
}
