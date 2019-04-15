package com.cn.demo.pojo.entity;

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
public class UserDO implements Serializable {

    private static final long serialVersionUID = -7793750140164938463L;

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private Integer age;
    private String phoneNum;
    private String email;
    private Date createTime;
    private Date updateTime;

}
