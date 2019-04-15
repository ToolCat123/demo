package com.cn.thymeleafdemo.controller;

import com.cn.thymeleafdemo.service.ThymeleafToPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yc
 */
@RestController
public class PdfController {

    @Resource
    private ThymeleafToPdfService thymeleafToPdfService;

    @GetMapping("/get_pdf")
    private void getPDF() throws Exception {
        thymeleafToPdfService.getPdf();
    }

    @GetMapping("/get_system_info")
    private void getSystemInfo() {
        System.out.println(System.getProperty("user.home"));
        System.getProperties().list(System.out);
    }

}
