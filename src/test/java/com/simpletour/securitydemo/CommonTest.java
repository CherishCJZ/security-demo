package com.simpletour.securitydemo;

import com.simpletour.securitydemo.data.Database;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: chenjunzhou
 * @Date: 2018/10/25
 */
public class CommonTest {


    @Test
    public void getMethodIdentifier() throws NoSuchMethodException {
        Method method = CommonTest.class.getMethod("getAuthorities", String.class, Database.class);
        System.out.println(ReflectionUtils.getMethodIdentifier(method));
    }


//    public List<GrantedAuthority> getAuthorities(String username,Database database) {
//        return Database.getAuthorities(username);
//    }

    public static final List<GrantedAuthority> getAuthorities(String username, Database database) {
        return Database.getUserAuthorities(username);
    }

}
