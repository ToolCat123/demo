package com.github.aliyunsmsdemo.service;

import com.github.aliyunsmsdemo.entity.vo.SmsVO;

public interface SmsService {

    /**
     * 发送短信
     *
     * @param smsVO 短信VO
     * @return 是否发送成功
     */
    Boolean sendSms(SmsVO smsVO);

}
