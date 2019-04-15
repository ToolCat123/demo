package com.cn.demo.pojo.dto.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery implements Serializable {

    private static final long serialVersionUID = -3710288935182553627L;

    private Long id;
    private String nickname;
    private String phoneNum;
    private String email;

}
