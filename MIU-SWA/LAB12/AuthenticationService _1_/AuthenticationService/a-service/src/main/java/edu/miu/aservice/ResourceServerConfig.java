package edu.miu.aservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/name").permitAll()
                .antMatchers("/salary").hasRole("MANAGER")
                .antMatchers("/phone").hasRole("USER")
                .antMatchers("/publicinfo").permitAll()
                .antMatchers("/managerinfo").hasRole("MANAGER")
                .antMatchers("/userinfo").hasRole("USER")
                .anyRequest()
                .authenticated();
    }
}
