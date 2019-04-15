package com.cn.aliyunossdemo.service;

import com.aliyun.oss.model.OSSObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yc
 */
public interface OssService {

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件访问地址
     */
    String upload(MultipartFile file);

    /**
     * 下载文件
     *
     * @param fileName 文件名
     * @return 文件下载地址
     */
    OSSObject download(String fileName);

    /**
     * 删除文件
     *
     * @param fileName 文件名
     * @return 是否删除成功
     */
    Boolean delete(String fileName);
}
