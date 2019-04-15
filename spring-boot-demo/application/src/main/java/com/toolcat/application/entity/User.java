package com.toolcat.application.entity;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * User实体
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 8523397057769218691L;

    @Id
    private Long id;        // id
    private String username;// 姓名
    private Long age;       // 年龄
    private String gender;  // 性别
    private String address; // 地址
    private String phone;   // 电话
    private String email;   // 邮箱
    private String password;// 密码
    private String roles;   // 角色
    private Date birthday;  // 生日

}
