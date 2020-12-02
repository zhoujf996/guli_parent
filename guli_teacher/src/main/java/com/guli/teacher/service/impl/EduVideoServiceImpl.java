package com.guli.teacher.service.impl;

import com.guli.teacher.client.VodClient;
import com.guli.teacher.entity.EduVideo;
import com.guli.teacher.mapper.EduVideoMapper;
import com.guli.teacher.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author guli
 * @since 2020-11-29
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    VodClient vodClient;

    @Override
    public Boolean removeVideoById(String id) {
        
        //TODO 删除阿里云上的视频
        //查询云端视频id
        EduVideo video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        //删除视频资源
        if (!StringUtils.isEmpty(videoSourceId)) {
            vodClient.removeVideo(id);
        }

        //删除数据库中的video
        int delete = baseMapper.deleteById(id);
        return delete == 1;
    }
}
