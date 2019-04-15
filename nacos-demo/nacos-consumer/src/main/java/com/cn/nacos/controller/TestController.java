package com.cn.nacos.controller;

import com.cn.nacos.api.RemoteTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author yc
 */
@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private Environment environment;

    @GetMapping("/get_config")
    public String getConfig() {
        String name = environment.getProperty("student.name");
        String age = environment.getProperty("student.age");
        logger.info("name:{},age:{}", name, age);
        return name + " " + age;
    }

    @GetMapping("/restTemplateConfig")
    public String restTemplateConfig() {
        String s = new RestTemplate().getForObject("http://127.0.0.1:8081/get_config", String.class);
        return s + "    restTemplate调用成功";
    }


    @Resource
//    @Autowired
    private RemoteTestService remoteTestService;

    @GetMapping("/feignConfig")
    public String feignConfig() {
        String s = remoteTestService.getConfig();
        return s + "     feign调用成功";
    }

}
