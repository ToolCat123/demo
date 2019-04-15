package com.toolcat.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 // 开启Swagger
public class SwaggerConfig {

    // 配置Swagger的Docket实例
    @Bean
    public Docket getDocket(Environment environment) {

        // 获取项目环境生产的时候关闭Swagger 生产环境关闭
        Profiles profiles = Profiles.of("pro");
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("toolCat")
                .apiInfo(getApiInfo())
                // Swagger开关
                .enable(!flag)
                .select()
                // 指定扫描
                .apis(RequestHandlerSelectors.basePackage("com.toolcat.application.controller"))
                // 过滤
//                .paths(PathSelectors.none())
                .build();
    }

    // 配置ApiInfo
    @Bean
    public ApiInfo getApiInfo() {
        return new ApiInfo(
                "application",
                "api",
                "1.0",
                "123",
                new Contact("toolCat", "localhost/swagger-ui.html", "923355209@qq.com"),
                "Apache 2.0",
                "www.baidu.com",
                new ArrayList());
    }

}
