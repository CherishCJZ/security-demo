package com.simpletour.securitydemo.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: chenjunzhou
 * @Date: 2018/9/30
 */
@Component
public class LoginAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final String DFAULT_TARGET_URL = "/index";

    private static final String SAVED_REQUEST = "SPRING_SECURITY_SAVED_REQUEST";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        DefaultSavedRequest savedRequest = (DefaultSavedRequest) session.getAttribute(SAVED_REQUEST);
        String uri = DFAULT_TARGET_URL;
        if (savedRequest != null) {
            uri = savedRequest.getRequestURI();
        }
        uri = uri + "?username=" + authentication.getName();
        new DefaultRedirectStrategy().sendRedirect(httpServletRequest, httpServletResponse, uri);
    }
}
