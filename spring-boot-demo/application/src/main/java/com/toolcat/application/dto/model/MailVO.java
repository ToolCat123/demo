package com.toolcat.application.dto.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 邮件 demo
 */
@Data
public class MailVO implements Serializable {

    private static final long serialVersionUID = -8552495434367139191L;

    @NotBlank(message = "收件人邮箱为空")
    @Length(min = 5, max = 255, message = "收件人邮箱{min}-{max}")
    private String consignee;// 收件人邮箱
    @NotBlank(message = "邮件标题为空")
    @Length(min = 5, max = 255, message = "邮件标题{min}-{max}")
    private String subject;// 邮件标题
    @Length(max = 255, message = "邮件内容不超过{max}")
    private String content;// 邮件内容
    private MultipartFile attachment;// 邮件附件
}
