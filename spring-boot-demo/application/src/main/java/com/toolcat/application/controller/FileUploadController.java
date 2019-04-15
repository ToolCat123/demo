package com.toolcat.application.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * 文件 上传 下载 demo
 */
@RestController
public class FileUploadController {

    private static final String FILE_DIRECTOR = "F:\\upload\\";

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
        Assert.notNull(file, "请选择一个文件");

        //UUID
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(FILE_DIRECTOR + fileName);

        try {
            Files.copy(file.getInputStream(), path, REPLACE_EXISTING);// 存在就覆盖
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("上传成功");
    }

    /**
     * 下载文件
     *
     * @param fileName
     * @return
     */
    @GetMapping("/{fileName}")
    public ResponseEntity download(@PathVariable("fileName") String fileName) {
        Path path = Paths.get(FILE_DIRECTOR + fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

//    @GetMapping("/fileName")
//    public ResponseEntity loadFile(@PathVariable("fileName") String fileName) {
//        Path path = Paths.get(FILE_DIRECTOR + fileName);
//        Resource resource = null;
//        try {
//            resource = new UrlResource(path.toUri());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//
//    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity deleteFile(@PathVariable("fileName") String fileName) {
        Path path = Paths.get(FILE_DIRECTOR + fileName);
        try {
            if (!FileSystemUtils.deleteRecursively(path)) {
                return ResponseEntity.ok("删除失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("删除成功");
    }

    /**
     * 查看所有文件
     *
     * @return
     */
    @GetMapping("/loadAll")
    public ResponseEntity loadAll() throws IOException {
        List<String> urlAll = Files.walk(Paths.get(FILE_DIRECTOR), 1)
                .filter(path -> !path.equals(Paths.get(FILE_DIRECTOR)))
                .map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "download", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList());
        return ResponseEntity.ok(urlAll);
    }

}
