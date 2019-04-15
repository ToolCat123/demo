package com.cn.jpademo.entity;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author yc
 */
@Data
public class TenantQuery {

    @NotNull(message = "主键不能为空")
    @Min(value = 1, message = "id最小为1")
    @Max(value = 999, message = "id最大为999")
    @Positive(message = "主键为正整数")// @Negative 整数
    private Long id;
    @NotBlank(message = "名称不能为空")
    @Size(min = 1, max = 8, message = "姓名长度必须为1到8")
    private String name;
    @NotEmpty(message = "列表不能为空")
    private List<Long> idList;

}
