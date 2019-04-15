package com.cn.thymeleafdemo.service.impl;

import com.cn.thymeleafdemo.service.ThymeleafToPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author yc
 */
@Service
public class ThymeleafToPdfServiceImpl implements ThymeleafToPdfService {

    @Resource
    TemplateEngine templateEngine;

    @Override
    public void getPdf() throws Exception {
        this.generatePdfFromHtml(parseThymeleafTemplate());
    }

    /**
     * 解析Thymeleaf模板
     *
     * @return String
     */
    private String parseThymeleafTemplate() {
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode(TemplateMode.HTML);
//        TemplateEngine templateEngine = new TemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
        Context context = new Context();
        context.setVariable("to", "Hello World");
        return templateEngine.process("pdf_thymeleaf", context);
    }

    /**
     * 生成PDF
     *
     * @param html html字符串
     */
    private void generatePdfFromHtml(String html) throws Exception {
//        String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
        String outputFolder = "D:/workspace/tem" + File.separator + "thymeleaf.pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        System.out.println("========PDF生成成功=========");
    }

}
