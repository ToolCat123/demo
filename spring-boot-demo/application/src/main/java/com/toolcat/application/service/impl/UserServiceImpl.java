package com.toolcat.application.service.impl;

import com.toolcat.application.entity.User;
import com.toolcat.application.mapper.UserMapper;
import com.toolcat.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Optional;

/**
 * userService
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<List<User>> allUser() {
        return Optional.ofNullable(userMapper.selectAll());
    }

    @Override
    public Optional<User> getById(Integer uid) {
        Assert.notNull(uid, "参数为null");
        return Optional.ofNullable(userMapper.selectByPrimaryKey(uid));
    }

    /**
     * 标准条件查询
     *
     * @param example
     * @return
     */
    @Override
    public Optional<List<User>> getByCriteria(Example example) {
        Assert.notNull(example, "参数为null");
        return Optional.ofNullable(userMapper.selectByExample(example));
    }

    /**
     * 查询用户
     *
     * @param account
     * @return
     */
    @Override
    public Optional<List<User>> getByAccount(String account) {
        Assert.hasText(account, "参数为null");
        User user = new User();
        user.setUsername(account);
        Example example = new Example(User.class).selectProperties("username", "password", "roles");
        example.createCriteria()
                .andEqualTo("username", account);
        List<User> userList = userMapper.selectByExample(example);
        return Optional.ofNullable(userList);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        Assert.hasText(username, "用户名为空");
        User user = new User();
        user.setUsername(username);
        return Optional.ofNullable((User) userMapper.select(user));
    }
}
