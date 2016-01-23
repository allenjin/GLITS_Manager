package com.grandland.glits.ms.config;

import com.grandland.glits.ms.service.GlmsUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

/**
 * SecurityConfig
 * Created by lwz on 2016/1/12.
 * spring security configuration
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new GlmsUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService());
        //指定密码加密所使用的加密器为passwordEncoder()
        // 需要将密码加密后写入数据库
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        //不删除凭据，以便记住用户
        auth.eraseCredentials(false);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置不拦截规则
        web.ignoring().antMatchers("/static/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置拦截规则
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")//只有管理员可访问页面
                .anyRequest().authenticated();

        //自定义登录界面
        http.csrf().disable().formLogin().loginPage("/login")
                .loginProcessingUrl("/logon")
                .defaultSuccessUrl("/")
                .permitAll()
                .successHandler(loginSuccessHandler())//登录成功后可使用loginSuccessHandler()存储用户信息
                .failureHandler(new SimpleUrlAuthenticationFailureHandler());

        // 自定义注销
        http.logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")//注销回到登录页面并且提示登出
                .permitAll()
                .deleteCookies("JSESSIONID")
                .deleteCookies("remember-me")
                .invalidateHttpSession(true);

         //session管理
        http.sessionManagement().sessionFixation().changeSessionId()
                .maximumSessions(1).expiredUrl("/");

        //remember-me配置
//        http.rememberMe().tokenValiditySeconds(360000).tokenRepository(tokenRepository());
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler(){
        return new SavedRequestAwareAuthenticationSuccessHandler();
    }
//    @Bean
//    public JdbcTokenRepositoryImpl tokenRepository(){
//        JdbcTokenRepositoryImpl j = new JdbcTokenRepositoryImpl();
//        j.setDataSource(new JdbcTemplate().getDataSource());
//        return j;
//    }
}
