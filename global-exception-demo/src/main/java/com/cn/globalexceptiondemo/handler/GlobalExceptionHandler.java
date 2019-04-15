package com.cn.globalexceptiondemo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * @author yc
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 数据库操作异常
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> sqlExceptionHandler(SQLException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok("数据库操作异常,请稍后重试或联系管理员");
    }

    /**
     * 0 不能为除数
     */
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Object> arithmeticExceptionHandler(ArithmeticException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok("0 不能为除数");
    }

    /**
     * 未知异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok("未知异常,请联系管理员");
    }

}
