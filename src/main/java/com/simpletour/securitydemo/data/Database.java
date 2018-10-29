package com.simpletour.securitydemo.data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Author: chenjunzhou
 * @Date: 2018/10/25
 */
public class Database {

    private static final Map<String, String> userPassworlds = new HashMap<>();
    private static final Map<String, List<GrantedAuthority>> userAuthorities = new HashMap<>();
    private static final Map<String, List<String>> urlAuthorities = new ConcurrentHashMap<>();

    private static final PasswordEncoder passwprdEncoder = new BCryptPasswordEncoder();

    static {
        addUser("张三", "123");
        addUserAuthorities("张三", "ROLE_ADMIN", "ROLE_领导", "ROLE_c");
        addUser("lisi", "123");
        addUserAuthorities("lisi", "ROLE_开发组长", "ROLE_c");
        addUser("wangwu", "123");
        addUserAuthorities("wangwu", "ROLE_啥几把不能做", "ROLE_c");

        addUrlAuthorities("/index", "permitAll");
        addUrlAuthorities("/logout", "permitAll");
        addUrlAuthorities("/login", "permitAll");
        addUrlAuthorities("/favicon.ico", "permitAll");
        addUrlAuthorities("/hello", "ROLE_ADMIN");
    }

    private static void addUser(String username, String passworld) {
        userPassworlds.put(username, passwprdEncoder.encode(passworld));
    }

    private static void addUrlAuthorities(String url, String... authorities) {
        List<String> collect = Arrays.stream(authorities).collect(Collectors.toList());
        urlAuthorities.put(url, collect);
    }

    public static String getPassworld(String username) {
        return userPassworlds.get(username);
    }

    private static void addUserAuthorities(String username, String... authorities) {
        userAuthorities.put(username, AuthorityUtils.createAuthorityList(authorities));
    }

    public static List<GrantedAuthority> getUserAuthorities(String username) {
        return userAuthorities.get(username);
    }


    public static List<String> getUrlAuthorities(String url, List<String> def) {
        return urlAuthorities.getOrDefault(url, def);
    }

}
