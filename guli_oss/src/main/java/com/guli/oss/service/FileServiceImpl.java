package com.guli.oss.service;

import com.aliyun.oss.OSSClient;
import com.guli.oss.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
    @Override
    public String upload(MultipartFile file) {
        OSSClient ossClient = null;
        String url = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClient(
                    ConstantPropertiesUtil.END_POINT,
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);

            //判断文件格式

            //判断文件内容

            //获取文件名称
            String filename = file.getOriginalFilename();
            //文件名字： lijin.shuai.jpg
            String ext = filename.substring(filename.lastIndexOf("."));
            String newName = UUID.randomUUID().toString() + ext;// ertyerxvnxrvjtcfhjktcfgh
            String dataPath = new DateTime().toString("yyyy/MM/dd");
            String urlPath = ConstantPropertiesUtil.FILE_HOST + "/" + dataPath + "/" + newName;
            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(ConstantPropertiesUtil.BUCKET_NAME, urlPath, inputStream);
            url = "https://"+ConstantPropertiesUtil.BUCKET_NAME + "." + ConstantPropertiesUtil.END_POINT + "/" + urlPath;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }

        return url;

    }
}
