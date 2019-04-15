package com.toolcat.application.dto.input;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * UserQuery
 */
@Data
public class UserQuery {

    private Long id;        // id
    @Length(min = 1, max = 32, message = "姓名长度范围{min}-{max}")
    private String username;// 姓名

}
