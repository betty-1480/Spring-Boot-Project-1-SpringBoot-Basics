package com.udacity.jwdnd.bettysavio.course1.cloudstorage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/* * You have to restrict unauthorized users from accessing pages other than the login and signup pages.
 * To do this, you must create a security configuration class that extends the WebSecurityConfigurerAdapter class from Spring.
 * Place this class in a package reserved for security and configuration.
 * Often this package is called security or config.*/

@Configuration // a class annotated with @configuration act as an extension to Spring's application context which is a central place where Spring scans for configuration files and components
@EnableWebSecurity // this is used to let Spring know that this clas is configuring SpringSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{ //HttpSecurity is provided by Spring framework to allow you to configure web based security for http requests
        httpSecurity.authorizeRequests()
                .antMatchers("/signup","/css/**","/js/**") // urls that are accessible to all users
                .permitAll() // permit the above urls without authorization
                .anyRequest().authenticated(); // authenticate all the other urls that are not in the antMatchers

        httpSecurity.formLogin() // Spring automatically generate the login form for us
                .loginPage("/login") //but we want to override it with ours, which is provided by Udacity
                .permitAll();

        httpSecurity.formLogin()   // add a default redirect on successful login to our home url
                .defaultSuccessUrl("/home",true);

        //TODO: implement logout
        httpSecurity.logout().logoutSuccessUrl("/login?logout").permitAll();
    }

}
