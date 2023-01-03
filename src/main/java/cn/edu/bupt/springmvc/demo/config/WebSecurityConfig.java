package cn.edu.bupt.springmvc.demo.config;

import cn.edu.bupt.springmvc.demo.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserService customUserService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //不拦截静态资源
        web.ignoring().antMatchers("/js/**", "/css/**", "/img/**", "/plugins/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许h2-console的frameset界面
        http.headers()
                .frameOptions().disable()
                //针对h2-console关闭csrf验证
                .and().csrf()
                .ignoringAntMatchers("/h2-console/**", "/app/**", "/check", "/register")
                .and().formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    System.out.println("登陆成功处理==============");
                    httpServletRequest.getSession().setAttribute("login_fail", "0");
                    System.out.println(authentication.getAuthorities().toString());
                    if(authentication.getAuthorities().toString().equals("[ROLE_USER]"))
                        System.out.println("User");
                    if(authentication.getAuthorities().toString().equals("[ROLE_ADMIN]"))
                        System.out.println("admin");
                    //跳转到首页
                    httpServletResponse.sendRedirect("/app/index");
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    System.out.println("登陆失败处理=============");
                    httpServletRequest.getSession().setAttribute("login_fail", "1");
                    //返回到登陆页面
                    httpServletResponse.sendRedirect("/login");

                })
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(((httpServletRequest, httpServletResponse, authentication) -> {
                    System.out.println("登出成功处理=============");
                    httpServletResponse.sendRedirect("/login");
                }))
                .and().authorizeRequests()
                .antMatchers("/h2-console/**", "/register", "/check").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/app/admin/**").hasRole("ADMIN")

                .anyRequest().authenticated();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder);
    }
}