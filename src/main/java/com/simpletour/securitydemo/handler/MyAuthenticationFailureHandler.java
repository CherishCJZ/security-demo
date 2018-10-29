package com.simpletour.securitydemo.handler;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: chenjunzhou
 * @Date: 2018/10/25
 */


public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    public MyAuthenticationFailureHandler(String defaultFailureUrl) {
        super(defaultFailureUrl);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = null;
        if (exception instanceof BadCredentialsException) {
            errorMessage = "用户名或密码错误";
        } else if (exception instanceof LockedException) {
            errorMessage = "当前用户已被锁定";
        } else if (exception instanceof AccountExpiredException) {
            errorMessage = "当前账户已过期";
        } else if (exception instanceof DisabledException) {
            errorMessage = "当前账号未激活";
        }
        request.getSession().setAttribute("loginErrorMessage", errorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }
}
