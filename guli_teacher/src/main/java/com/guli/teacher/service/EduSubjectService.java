package com.guli.teacher.service;

import com.guli.teacher.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.teacher.entity.vo.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author guli
 * @since 2020-11-25
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 根据传递的Excel模板导入课程分类
     * @param file
     * @return 错误的集合信息
     */
    List<String> importExcel(MultipartFile file);

    /**
     * 获取课程分类树状数据
     * @return
     */
    List<OneSubject> getTree();
}
