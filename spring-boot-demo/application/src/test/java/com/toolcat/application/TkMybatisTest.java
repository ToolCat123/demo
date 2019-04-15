package com.toolcat.application;

import com.toolcat.application.entity.User;
import com.toolcat.application.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TkMybatisTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有
     */
    @Test
    void test0() {
        List<User> userList = userMapper.selectAll();
        System.out.println(userList);
    }
}
