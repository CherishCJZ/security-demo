package com.simpletour.securitydemo.config;

import com.simpletour.securitydemo.metasourece.MyMetaSource;
import com.simpletour.securitydemo.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.annotation.Resource;

/**
 * @Author: chenjunzhou
 * @Date: 2018/9/30
 */

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailServiceImpl userDetailService;

    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;


    @Resource
    private AccessDeniedHandler accessDeniedHandler;

    @Resource
    private MyMetaSource myMetaSource;

    @Resource
    private AccessDecisionManager accessDecisionManager;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

//                .authorizeRequests().withObjectPostProcessor(objectPostProcessor())
                .authorizeRequests().accessDecisionManager(accessDecisionManager).anyRequest().permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .formLogin()
                //指定登录页的路径
                .loginPage("/login")
                //指定自定义form表单请求的路径
                .loginProcessingUrl("/authentication/form")
                .defaultSuccessUrl("/index")
//                .failureUrl("/login?error=true")
                .failureHandler(authenticationFailureHandler)
//                .successHandler(successHandler)
                //必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
                .and()
                .httpBasic();


        //默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
        http.csrf().disable();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }


    /*public ObjectPostProcessor objectPostProcessor() {
        return new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                object.setSecurityMetadataSource(myMetaSource);
                object.setAccessDecisionManager(accessDecisionManager);
                return object;
            }
        };
    }*/
}
