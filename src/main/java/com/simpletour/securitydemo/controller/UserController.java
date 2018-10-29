package com.simpletour.securitydemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: chenjunzhou
 * @Date: 2018/9/30
 */
@Controller
public class UserController {


    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @GetMapping("/login")
    public String login() {
        System.out.println("login......");
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        System.out.println(handlerMethods.keySet().iterator().next().getPatternsCondition().getPatterns());
        System.out.println(handlerMethods.keySet().iterator().next().getMethodsCondition().getMethods());
        System.out.println(handlerMethods.values().iterator().next().getBeanType());
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "index";
    }


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping(value = "index",name = "2222222")
    public ModelAndView index() {
        ModelAndView loginView = new ModelAndView("index");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        loginView.addObject("username", authentication.getName());
        loginView.addObject("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return loginView;
    }




}
