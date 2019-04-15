package com.toolcat.application.config.security.service;


import com.toolcat.application.entity.User;
import com.toolcat.application.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 自定义userDetailsService - 认证用户详情
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

        Optional<List<User>> userOptional = userService.getByAccount(account);

        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        //设置用户权限
        User user = userOptional.get().get(0);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (StringUtils.isNotBlank(user.getRoles())) {
            String[] roles = user.getRoles().split(",");
            for (String role : roles) {
                if (StringUtils.isNotBlank(role)) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.trim()));
                }
            }
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

}
