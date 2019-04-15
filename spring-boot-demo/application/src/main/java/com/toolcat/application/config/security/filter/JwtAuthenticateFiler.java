package com.toolcat.application.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toolcat.application.config.security.MySecurityConstants;
import com.toolcat.application.config.security.dto.SecurityUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.joda.time.DateTime;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 添加token
 */
@Log4j2
public class JwtAuthenticateFiler extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticateFiler(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl(MySecurityConstants.AUTH_LOGIN_URL);
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        SecurityUser user = objectMapper.readValue(request.getInputStream(), SecurityUser.class);
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        return this.authenticationManager.authenticate(userToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetails user = (UserDetails) authResult.getPrincipal();

        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // 创建token
        // 设置自己的key 获取自己的token
        SecretKey key = Keys.hmacShaKeyFor(MySecurityConstants.JWT_SECRET.getBytes());
        String token = Jwts.builder()
                .setHeaderParam("typ", MySecurityConstants.TOKEN_TYPE)
                .setIssuer(MySecurityConstants.TOKEN_ISSUER)
                .setAudience(MySecurityConstants.TOKEN_AUDIENCE)
                // 初始时间
                .setIssuedAt(new Date())
                // 如果当前时间在nbf里的时间之前，则Token不被接受
//                .setNotBefore(new DateTime().plusMinutes(3).toDate())
                // 过期时间
                .setExpiration(new DateTime().plusHours(1).toDate())
                .setSubject(user.getUsername())
                .claim("rol", roles)
                .signWith(key).compact();

        response.setHeader(MySecurityConstants.TOKEN_HEADER, MySecurityConstants.TOKEN_PREFIX + token);
    }

}
