package com.laurus.wmcs.service.impl;

import com.laurus.wmcs.security.JwtUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * JwtUserDetailsService
 * 实现UserDetailsService,重写loadUserByUsername方法
 * 返回随机生成的user,pass是密码,这里固定生成的
 * 如果你自己需要定制查询user的方法,请改造这里
 *
 * @author zhengkai.blog.csdn.net
 */
@Service
@Order(0)
public class JwtUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) {
        String pass = new BCryptPasswordEncoder().encode("pass");
        if (StringUtils.isNotEmpty(username) && username.contains("user")) {
            return new JwtUser(UUID.randomUUID().toString(), username, pass, "USER", true);
        } else {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
    }
}
