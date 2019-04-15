package com.cn.tkmybatisdemo.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


/**
 * @author yc
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket getDocket(Environment environment) {

        // 获取项目环境生产的时候关闭Swagger 生产环境关闭
        Profiles profiles = Profiles.of("pro");
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("toolCat")
                .apiInfo(getApiInfo())
                // Swagger开关
//                .enable(!flag)
                .select()
                // 指定扫描
                .apis(RequestHandlerSelectors.basePackage("com.cn.demo.controller"))
                // 过滤
//                .paths(PathSelectors.none())
                .paths(PathSelectors.any())
                .build();
    }

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
