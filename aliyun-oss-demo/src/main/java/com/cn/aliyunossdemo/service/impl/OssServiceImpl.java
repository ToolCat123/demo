package com.cn.aliyunossdemo.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.cn.aliyunossdemo.service.OssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author yc
 */
@Service
public class OssServiceImpl implements OssService {

    @Value("${aliyun.oss.endpoint:''}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId:''}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret:''}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName:''}")
    private String bucketName;

    /**
     * 创建OOSClient
     *
     * @return OSS
     */
    private OSS getOSSClient() {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            System.out.println("您的Bucket不存在，创建Bucket：" + bucketName + "。");
            ossClient.createBucket(bucketName);
        }
        return ossClient;
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件名
     */
    @Override
    public String upload(MultipartFile file) {
        OSS ossClient = this.getOSSClient();
        String fileName = UUID.randomUUID().toString().concat("-").concat(Objects.requireNonNull(file.getOriginalFilename()));
        String uri;
        try {
            // 上传文件
            ossClient.putObject(bucketName, fileName, file.getInputStream());
            // 获取文件地址
            uri = download(fileName).getResponse().getUri();
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        } finally {
            ossClient.shutdown();
        }
        return uri;
    }

    /**
     * 下载文件
     *
     * @param fileName 文件名
     * @return oss对象
     */
    @Override
    public OSSObject download(String fileName) {
        OSS ossClient = this.getOSSClient();
        OSSObject ossObject = null;
        try {
            ossObject = ossClient.getObject(bucketName, fileName);
        } catch (OSSException | ClientException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return ossObject;
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名
     * @return 删除成功
     */
    @Override
    public Boolean delete(String fileName) {
        OSS ossClient = this.getOSSClient();
        try {
            ossClient.deleteObject(bucketName, fileName);
            return false;
        } catch (OSSException | ClientException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return true;
    }
}
