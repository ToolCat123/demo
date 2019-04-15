package com.cn.jpademo.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * tenant
 *
 * @author
 */
@Data
@Entity
@DynamicUpdate
public class Tenant implements Serializable {
    /**
     * 主键ID
     */
    @Id
    private Long id;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 企业编码
     */
    private String code;

    /**
     * 类型 #{CREATE:创建;REGISTER:注册}
     */
    private String type;

    /**
     * 0-正常，9-锁定
     */
    private String lockFlag;

    /**
     * 0-正常，1-删除
     */
    private String delFlag;

    /**
     * 有效期 为空表示永久
     */
    private Date expirationTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人ID
     */
    private Long createId;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人ID
     */
    private Long updateId;

    private static final long serialVersionUID = 1L;
}