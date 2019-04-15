package com.cn.validationdemo.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * @author yc
 */
public class ValidationModel {

    @AssertFalse(message = "必须为false")
    private Boolean assertFalse;
    @AssertFalse(message = "必须为true")
    private Boolean assertTrue;

    @DecimalMax(value = "12.3", message = "小于等于{value}")
    private Float decimalMax;
    @DecimalMin(value = "10.3", message = "大于等于{value}")
    private Float decimalMin;
    @Min(value = 1, message = "最小值是{value}")
    @Max(value = 6, message = "最大值是{value}")
    private Integer number;
    @Digits(integer = 5, fraction = 3, message = "整数部分不超过5位数,小数部分不超过3位数")
    private Float digits;
    @Range(min = 1, max = 1000, message = "数字或者字符范围在{min}-{max}")
    private Double range;

    @Email(message = "字段不符合Email")
    private String email;
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "需要匹配{regexp}")
    private String phoneNumber;

    @Future(message = "时间是未来")
    private Date future;
    @FutureOrPresent(message = "不能是过去时间")
    private Date futureOrPresent;
    @Past(message = "时间是过去")
    private Date past;
    @PastOrPresent(message = "不能是未来时间")
    private Date pastOrPresent;

    @Negative(message = "必须为负数")
    private Integer negative;
    @NegativeOrZero(message = "不为正数")
    private Integer negativeOrZero;
    @Positive(message = "必须为正数")
    private Integer positive;
    @PositiveOrZero(message = "不为负数")
    private Integer positiveOrZero;

    @NotNull(message = "元素不为null")
    private Object notNull;
    @NotBlank(message = "字符串不为空")
    @Length(min = 1, max = 32, message = "字符串长度范围在{min}-{max}")
    private String notBlank;
    @NotEmpty(message = "数组或集合不能为空")
    @Size(min = 1, max = 4, message = "数组或集合元素范围在{min}-{max}")
    private List<Object> notEmpty;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }

    public Boolean getAssertFalse() {
        return assertFalse;
    }

    public void setAssertFalse(Boolean assertFalse) {
        this.assertFalse = assertFalse;
    }

    public Boolean getAssertTrue() {
        return assertTrue;
    }

    public void setAssertTrue(Boolean assertTrue) {
        this.assertTrue = assertTrue;
    }

    public Float getDecimalMax() {
        return decimalMax;
    }

    public void setDecimalMax(Float decimalMax) {
        this.decimalMax = decimalMax;
    }

    public Float getDecimalMin() {
        return decimalMin;
    }

    public void setDecimalMin(Float decimalMin) {
        this.decimalMin = decimalMin;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getDigits() {
        return digits;
    }

    public void setDigits(Float digits) {
        this.digits = digits;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFuture() {
        return future;
    }

    public void setFuture(Date future) {
        this.future = future;
    }

    public Date getFutureOrPresent() {
        return futureOrPresent;
    }

    public void setFutureOrPresent(Date futureOrPresent) {
        this.futureOrPresent = futureOrPresent;
    }

    public Date getPast() {
        return past;
    }

    public void setPast(Date past) {
        this.past = past;
    }

    public Date getPastOrPresent() {
        return pastOrPresent;
    }

    public void setPastOrPresent(Date pastOrPresent) {
        this.pastOrPresent = pastOrPresent;
    }

    public Integer getNegative() {
        return negative;
    }

    public void setNegative(Integer negative) {
        this.negative = negative;
    }

    public Integer getNegativeOrZero() {
        return negativeOrZero;
    }

    public void setNegativeOrZero(Integer negativeOrZero) {
        this.negativeOrZero = negativeOrZero;
    }

    public Integer getPositive() {
        return positive;
    }

    public void setPositive(Integer positive) {
        this.positive = positive;
    }

    public Integer getPositiveOrZero() {
        return positiveOrZero;
    }

    public void setPositiveOrZero(Integer positiveOrZero) {
        this.positiveOrZero = positiveOrZero;
    }

    public Object getNotNull() {
        return notNull;
    }

    public void setNotNull(Object notNull) {
        this.notNull = notNull;
    }

    public String getNotBlank() {
        return notBlank;
    }

    public void setNotBlank(String notBlank) {
        this.notBlank = notBlank;
    }

    public List<Object> getNotEmpty() {
        return notEmpty;
    }

    public void setNotEmpty(List<Object> notEmpty) {
        this.notEmpty = notEmpty;
    }
}
