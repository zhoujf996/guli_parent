package com.guli.oss.service;

import com.guli.common.result.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     * 上传图片
     * @param file
     * @return 返回图片地址
     */
    String upload(MultipartFile file);
    
}
