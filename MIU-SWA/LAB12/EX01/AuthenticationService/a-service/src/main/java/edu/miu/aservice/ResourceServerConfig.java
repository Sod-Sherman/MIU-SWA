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
                .antMatchers("/products").hasAnyRole(Role.CUSTOMER.toString(), Role.EMPLOYEE.toString(), Role.MANAGER.toString())
                .antMatchers("/productdata").hasAnyRole(Role.CUSTOMER.toString(), Role.EMPLOYEE.toString(), Role.MANAGER.toString())
                .antMatchers("/phone").hasAnyRole(Role.EMPLOYEE.toString(), Role.MANAGER.toString())
                .antMatchers("/contact").hasAnyRole(Role.EMPLOYEE.toString(), Role.MANAGER.toString())
                .antMatchers("/salary").hasRole(Role.MANAGER.name())

                .antMatchers("/name").permitAll()
                .antMatchers("/publicinfo").permitAll()
                .antMatchers("/managerinfo").hasRole(Role.MANAGER.name())
                .antMatchers("/userinfo").hasRole(Role.CUSTOMER.name())
                .anyRequest()
                .authenticated();
    }
}
