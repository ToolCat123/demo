package com.github.aliyunsmsdemo.controller;

import com.github.aliyunsmsdemo.entity.vo.SmsVO;
import com.github.aliyunsmsdemo.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.*;

@Validated
@RestController
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/send_sms")
    private Boolean sendSms(SmsVO smsVO) {
        return smsService.sendSms(smsVO);
    }

    @GetMapping("/test1/{length}")
    public ResponseEntity<Object> test1(@Positive @Max(value = 9, message = "参数不超过{value}") @PathVariable("length") Long length) {
        double random = Math.random() * 9 + 1;
        for (int i = 1; i < Math.abs(length); i++) {
            random *= 10;
        }
        return ResponseEntity.ok(String.valueOf((int) random));
    }

    @GetMapping("/test2/{length}")
    public ResponseEntity<Object> test2(@PositiveOrZero @PathVariable("length") Long length) {
        StringBuilder stringBuffer = new StringBuilder();
        for (long i = 0; i < Math.abs(length); i++) {
            stringBuffer.append((long) (Math.random() * 9 + 1));
        }
        return ResponseEntity.ok(stringBuffer.toString());
    }

}
