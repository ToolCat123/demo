package com.github.aliyunsmsdemo.entity.vo;

public class SmsVO {

    /**
     * 发送的短信模板
     */
    private String template;

    /**
     * 发送的手机号码
     */
    private String phone;

    /**
     * 短信验证码
     */
    private String code;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
