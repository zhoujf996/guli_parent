package com.guli.teacher.service;

import com.guli.teacher.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.teacher.entity.vo.OneChapter;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author guli
 * @since 2020-11-26
 */
public interface EduChapterService extends IService<EduChapter> {


    /**
     * 根据课程ID查询章节和小节列表
     * @param id
     * @return
     */
    List<OneChapter> getChapterAndVideoById(String id);
}
