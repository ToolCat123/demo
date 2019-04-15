package com.toolcat.application.service;

import com.toolcat.application.entity.User;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Optional;

/**
 * userService
 */
public interface UserService {

    Optional<List<User>> allUser();

    Optional<User> getById(Integer uid);

    Optional<List<User>> getByCriteria(Example example);

    Optional<List<User>> getByAccount(String account);

    Optional<User> getByUsername(String username);

}
