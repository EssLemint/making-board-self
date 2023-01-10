package com.todo.listup.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Slf4j
@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

    log.info("===== SecurityConfig =====  START =====");

    http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().csrf().disable().cors()
        .and()
        .addFilterBefore(new SecurityFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests()
          .requestMatchers("/new/member", "/sign/user", "/test/cont").permitAll()
          .requestMatchers("*/member/*").hasRole("USER")
        .and().formLogin()
        .successForwardUrl("/test/cont")
        .and().httpBasic();

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
