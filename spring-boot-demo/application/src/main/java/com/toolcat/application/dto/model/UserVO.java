package com.toolcat.application.dto.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * UserVO
 */
@Data
public class UserVO {

    private Long id;        // id
    @Length(min = 1, max = 32, message = "姓名长度范围{min}-{max}")
    private String username;// 姓名
    private Long age;       // 年龄
    private String gender;  // 性别
    @Length(min = 1, max = 255, message = "地址长度范围{min}-{max}")
    private String address; // 地址
    @Length(min = 4, max = 16, message = "电话长度范围{min}-{max}")
    private String phone;   // 电话
    @Length(min = 4, max = 64, message = "邮箱长度范围{min}-{max}")
    private String email;   // 邮箱
    @Length(min = 4, max = 64, message = "密码长度范围{min}-{max}")
    private String password;// 密码
    @Length(max = 255, message = "角色范围不超过{max}")
    private String roles;   // 角色

}
