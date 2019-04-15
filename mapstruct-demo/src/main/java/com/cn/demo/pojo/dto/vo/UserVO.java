package com.cn.demo.pojo.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {

    private static final long serialVersionUID = 3310006777633020200L;

    private Long id;
    private String nickname;
    private Integer age;
    private String phoneNum;
    private String email;
    private Date createTime;

}
