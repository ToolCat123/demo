package com.toolcat.application.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * security 配置
 */
@Configuration
@EnableWebSecurity //开启Spring Security
/*
securedEnabled 拦截 @Secured
prePostEnabled 拦截 @PreAuthorize & @PostAuthorize(配合Spring EL表达式)
*/
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests() // 认证配置
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/api/guest").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and().httpBasic()
                .and()
                .formLogin()
                .and()
//                .addFilter(new JwtAuthenticateFiler(authenticationManager()))
//                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/favicon.ico"
                , "/index.html"
                , "/js/**"
                , "/css/**"
                , "/img/**"
                , "/fonts/**"
                , "/verifyCode"
                , "/swagger-ui.html");
    }

}
