package com.simpletour.securitydemo.config;

import com.simpletour.securitydemo.handler.MyAuthenticationFailureHandler;
import com.simpletour.securitydemo.voter.RoleBasedVoter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: chenjunzhou
 * @Date: 2018/10/26
 */
@Configuration
public class WebSecurityConfiguration {


    private ExceptionTranslationFilter exceptionTranslationFilter;

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new MyAuthenticationFailureHandler("/login?error=true");
    }


    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters
                = Arrays.asList(
                new WebExpressionVoter(),
                // new RoleVoter(),
                new RoleBasedVoter(),
                new AuthenticatedVoter());
        return new UnanimousBased(decisionVoters);
    }
}
