package com.laurus.wmcs.controller;

import com.laurus.wmcs.Utils.JwtTokenUtil;
import com.laurus.wmcs.result.Result;
import com.laurus.wmcs.security.JwtRequest;
import com.laurus.wmcs.security.JwtResponse;
import com.laurus.wmcs.security.JwtUser;
import com.laurus.wmcs.service.impl.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * JwtAuthenticationController
 * 包含登陆和查看token的方法
 *
 * @author zhengkai.blog.csdn.net
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @PostMapping("${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        System.out.println("username:" + authenticationRequest.getUsername() + ",password:" + authenticationRequest.getPassword());
        try {

            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(new Result(false, "用户名密码错误", null));
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @GetMapping("/token")
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }
}
