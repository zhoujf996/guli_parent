package com.guli.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.teacher.entity.EduChapter;
import com.guli.teacher.entity.EduVideo;
import com.guli.teacher.entity.vo.OneChapter;
import com.guli.teacher.entity.vo.TwoVideo;
import com.guli.teacher.mapper.EduChapterMapper;
import com.guli.teacher.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.teacher.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author guli
 * @since 2020-11-26
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<OneChapter> getChapterAndVideoById(String id) {

        List<OneChapter> list = new ArrayList<>();
        //判断ID
        //1.根据课程ID查询章节列表
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        wrapper.orderByAsc("sort");
        List<EduChapter> chapterList = baseMapper.selectList(wrapper);
        //判断集合
        //2.遍历章节列表
        for (EduChapter chapter : chapterList) {

            //3.把每一个章节对象复制到OneChapter
            OneChapter oneChapter = new OneChapter();
            BeanUtils.copyProperties(chapter, oneChapter);
            //4.根据每一个章节ID查询小节列表
            QueryWrapper<EduVideo> wr = new QueryWrapper<>();
            wr.eq("chapter_id", chapter.getId());
            wr.orderByAsc("sort");
            List<EduVideo> videoList = eduVideoService.list(wr);
            //5.遍历每一个小节
            for (EduVideo video : videoList) {
                //6.把每一个小节对象复制到TwoVideo
                TwoVideo twoVideo = new TwoVideo();
                BeanUtils.copyProperties(video, twoVideo);
                //7.把每一个TwoVideo加到章节children里面
                oneChapter.getChildren().add(twoVideo);
            }
            //8.把每一个章节加到总集合中
            list.add(oneChapter);
        }
        return list;
    }
}
