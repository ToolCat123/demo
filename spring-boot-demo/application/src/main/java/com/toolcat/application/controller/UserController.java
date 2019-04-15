package com.toolcat.application.controller;


import com.github.pagehelper.PageHelper;
import com.toolcat.application.dto.input.UserQuery;
import com.toolcat.application.dto.model.UserVO;
import com.toolcat.application.dto.model.UserVOMapper;
import com.toolcat.application.dto.output.JSONResult;
import com.toolcat.application.entity.User;
import com.toolcat.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Optional;

/**
 * userController
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public JSONResult<List<UserVO>> allUser(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "2") Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Optional<List<User>> userList = userService.allUser();

        return userList.map(users -> JSONResult.success(UserVOMapper.INSTANCE.toVO(users))).orElseGet(() -> JSONResult.failMsg("用户列表为空"));
    }

    @GetMapping("/{uid}")
    public JSONResult<UserVO> getById(@PathVariable("uid") Integer uid) {
        Assert.notNull(uid, "uid为空");
        Optional<User> user = userService.getById(uid);
        return user.map(u -> JSONResult.success(UserVOMapper.INSTANCE.toVO(u))).orElseGet(() -> JSONResult.failMsg("用户不存在"));
    }

    @GetMapping("/query")
    public JSONResult<List<UserVO>> getByCriteria(UserQuery userQuery) {
        Assert.notNull(userQuery, "userQuery为空");
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        if (userQuery.getId() != null) {
            criteria.andEqualTo("id", userQuery.getId());
        }

        if (userQuery.getUsername() != null) {
            criteria.andEqualTo("name", userQuery.getUsername());
        }

        Optional<List<User>> userList = userService.getByCriteria(example);

        return userList.map(users -> JSONResult.success(UserVOMapper.INSTANCE.toVO(users))).orElseGet(() -> JSONResult.failMsg("查询结果为空"));
    }

}
