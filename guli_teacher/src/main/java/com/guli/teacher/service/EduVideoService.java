package com.guli.teacher.service;

import com.guli.teacher.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author guli
 * @since 2020-11-29
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 根据Video的ID删除视频
     * @param id
     * @return
     */
    Boolean removeVideoById(String id);
}
