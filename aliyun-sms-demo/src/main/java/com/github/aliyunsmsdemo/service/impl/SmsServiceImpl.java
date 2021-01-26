package com.github.aliyunsmsdemo.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.aliyunsmsdemo.entity.vo.SmsVO;
import com.github.aliyunsmsdemo.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SmsServiceImpl implements SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Value("${aliyun.sms.regionId:''}")
    private String regionId;
    @Value("${aliyun.sms.systemDomain:''}")
    private String systemDomain;
    @Value("${aliyun.sms.systemVersion:''}")
    private String systemVersion;
    @Value("${aliyun.sms.accessKeyId:''}")
    private String accessKeyId;
    @Value("${aliyun.sms.secret:''}")
    private String secret;
    @Value("${aliyun.sms.signName:''}")
    private String signName;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * @return AcsClient
     */
    private IAcsClient getDefaultAcsClient() {
        DefaultProfile defaultProfile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        return new DefaultAcsClient(defaultProfile);
    }

    /**
     * @return CommonRequest
     */
    private CommonRequest getCommonRequest() {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(systemDomain);
        request.setSysVersion(systemVersion);
        request.setSysAction("SendSms");
        request.putQueryParameter("SignName", signName);
        /*request.putQueryParameter("RegionId", regionId);*/
        return request;
    }

    /**
     * 发送短信
     *
     * @param smsVO 短信VO
     * @return 是否发送成功
     */
    @Override
    public Boolean sendSms(SmsVO smsVO) {
        smsVO.setCode(this.getRandomCode());
        IAcsClient client = getDefaultAcsClient();
        CommonRequest request = getCommonRequest();
        request.putQueryParameter("PhoneNumbers", smsVO.getPhone());
        request.putQueryParameter("TemplateCode", smsVO.getTemplate());
        Map<String, String> map = new HashMap<>(1);
        map.put("code", smsVO.getCode());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            request.putQueryParameter("TemplateParam", objectMapper.writeValueAsString(map));
            CommonResponse response = client.getCommonResponse(request);
            /*redisTemplate.setKeySerializer(new StringRedisSerializer());*/
            // 将验证码存入redis中并设置key=模板:手机号,有效期为2分钟
            redisTemplate.opsForValue().set(smsVO.getTemplate() + ":" + smsVO.getPhone(), smsVO.getCode(), 60 * 2, TimeUnit.SECONDS);
            logger.info("短信发送成功 -> 发送模板:{}, 手机号:{}, 验证码:{}, 结果:{}", smsVO.getTemplate(), smsVO.getPhone(), smsVO.getCode(), response.getData());
            return true;
        } catch (Exception e) {
            logger.info("短信发送失败 -> 发送模板:{}, 手机号:{}, 验证码:{}", smsVO.getTemplate(), smsVO.getPhone(), smsVO.getCode());
            return false;
        }
    }

    /**
     * @return 获取6位随机数字字符串
     */
    private String getRandomCode() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }

}
