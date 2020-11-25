package com.guli.oss.controller;

import com.guli.common.result.Result;
import com.guli.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("oss")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传文件
     * 接收请求
     * 返回响应
     */
    @PostMapping("file/upload")
    public Result upload(MultipartFile file) {
        
        String url = fileService.upload(file);
        
        return Result.ok().data("url", url);
    }

}
