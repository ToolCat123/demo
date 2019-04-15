package com.cn.aliyunossdemo.controller;

import com.aliyun.oss.model.OSSObject;
import com.cn.aliyunossdemo.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yc
 */
@RestController
public class TestController {

    @Autowired
    private OssService ossService;

    /**
     * 上传文件
     *
     * @param file 文件
     */
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) {
        return ossService.upload(file);
    }

    /**
     * 下载文件
     *
     * @param fileName 文件名
     */
    @GetMapping("/download")
    public ResponseEntity<Object> download(String fileName) {
        OSSObject ossObject = ossService.download(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + fileName + "\"")
                .body(ossObject.getResponse().getUri());
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名
     */
    @DeleteMapping("/delete")
    public Boolean delete(String fileName) {
        return ossService.delete(fileName);
    }
}
