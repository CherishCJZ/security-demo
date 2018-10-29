package com.simpletour.securitydemo.voter;

import com.simpletour.securitydemo.data.Database;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chenjunzhou
 * @Date: 2018/10/26
 */
public class RoleBasedVoter implements AccessDecisionVoter<FilterInvocation> {


    private static final String permitAll = "permitAll";
    private static final String denyAll = "denyAll";
    private static final String anonymous = "anonymous";
    private static final String authenticated = "authenticated";
    private static final String fullyAuthenticated = "fullyAuthenticated";
    private static final String rememberMe = "rememberMe";

    private static final List<String> DEFAULT_AUTHORITY = new ArrayList<>();


    static {
        DEFAULT_AUTHORITY.add(permitAll);
    }


    @Override
    public boolean supports(ConfigAttribute attribute) {
//        return attribute instanceof SecurityConfig;
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> attributes) {
        String requestUrl = filterInvocation.getRequestUrl();
        List<String> urlAuthorities = Database.getUrlAuthorities(requestUrl, DEFAULT_AUTHORITY);

        if (urlAuthorities.contains("permitAll")) {
            return 1;
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new InsufficientAuthenticationException("请首先的登录");
        }
        List<String> userAuthorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        if (userAuthorities.containsAll(urlAuthorities)) {
            return 1;
        }
        return -1;


    }
}
