package com.simpletour.securitydemo.metasourece;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: chenjunzhou
 * @Date: 2018/10/26
 */

@Component
public class MyMetaSource implements FilterInvocationSecurityMetadataSource {

    private static final List<ConfigAttribute> list = new ArrayList<>();

    static {
        ConfigAttribute configAttribute = new SecurityConfig("permitAll");
        list.add(configAttribute);
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (FilterInvocation.class.isAssignableFrom(object.getClass())) {
            return list;
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return list;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
