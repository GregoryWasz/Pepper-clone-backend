package com.pepper.backend.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userService")
    @Autowired
    UserDetailsService userDetailsService;

    private RestAuthenticationSuccessHandler authenticationSuccessHandler;
    private RestAuthenticationFailureHandler authenticationFailureHandler;
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder,
                          @Qualifier("userService") UserDetailsService userDetailsService,
                          RestAuthenticationSuccessHandler authenticationSuccessHandler,
                          RestAuthenticationFailureHandler authenticationFailureHandler) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception{
    JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter();
    filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
    filter.setAuthenticationFailureHandler(authenticationFailureHandler);
    filter.setAuthenticationManager(super.authenticationManager());
    return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/v1/users/register").permitAll()
                .antMatchers("/api/v1/users").permitAll()
                //TODO SET ROLES TO ADMIN ONLY
                .antMatchers("/api/v1/roles").hasAnyRole("USER")
                //TODO SET TAGS TO DELETE ADMIN ONLY
                .antMatchers("/api/v1/tags").permitAll()
                .antMatchers("/api/v1/posts").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        http
                .cors()
                .and()
                .csrf()
                .disable();
    }
}
