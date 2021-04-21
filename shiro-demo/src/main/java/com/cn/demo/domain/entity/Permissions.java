package com.cn.demo.domain.entity;

/**
 * @author yc
 * @date 2021/3/23
 */

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Permissions {
    private String id;
    private String permissionsName;
}
