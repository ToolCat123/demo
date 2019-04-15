package com.cn.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 企业
 * </p>
 *
 * @author yc
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Tenant对象", description="企业")
public class Tenant implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "企业名称")
    private String name;

    @ApiModelProperty(value = "企业编码")
    private String code;

    @ApiModelProperty(value = "类型#{CREATE:创建;REGISTER:注册}")
    private String type;

    @ApiModelProperty(value = "0-正常，9-锁定")
    private String lockFlag;

    @ApiModelProperty(value = "0-正常，1-删除")
    @TableLogic
    private String delFlag;

    @ApiModelProperty(value = "有效期 为空表示永久")
    private Date expirationTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "创建人ID")
    private Long createId;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "修改人ID")
    private Long updateId;


}
