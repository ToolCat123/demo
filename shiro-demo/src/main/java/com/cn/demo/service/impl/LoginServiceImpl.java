package com.cn.demo.service.impl;

/**
 * @author yc
 * @date 2021/3/23
 */
import com.cn.demo.domain.entity.Permissions;
import com.cn.demo.domain.entity.Role;
import com.cn.demo.domain.entity.User;
import com.cn.demo.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public User getUserByName(String getMapByName) {
        return getMapByName(getMapByName);
    }

    /**
     * 模拟数据库查询
     *
     * @param userName 用户名
     * @return User
     */
    private User getMapByName(String userName) {

        Permissions permissions1 = new Permissions("1", "query");
        Permissions permissions2 = new Permissions("2", "add");

        Set<Permissions> permissionsSet1 = new HashSet<>();
        permissionsSet1.add(permissions1);
        permissionsSet1.add(permissions2);
        Role role1 = new Role("1", "admin", permissionsSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        User user1 = new User("1", "admin", "123456", roleSet1);
        Map<String, User> map = new HashMap<>();
        map.put(user1.getUserName(), user1);

        Set<Permissions> permissionsSet2 = new HashSet<>();
        permissionsSet2.add(permissions1);
        Role role2 = new Role("2", "user", permissionsSet1);
        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(role2);
        User user2 = new User("2", "user", "123456", roleSet2);
        map.put(user2.getUserName(), user2);

        return map.get(userName);
    }
}
