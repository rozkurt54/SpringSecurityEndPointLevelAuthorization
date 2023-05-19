package com.rozkurt.ss_2022_c5_e1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/demo").hasAnyAuthority("write", "read")
                .requestMatchers("/test").hasAnyAuthority("read")
                .requestMatchers("/open/**").permitAll()
                .anyRequest().authenticated() //end point level authorization
                .and().build();

    }

    @Bean
    public UserDetailsService userDetailsService() {

        var uds = new InMemoryUserDetailsManager();

        var u1 = User.withUsername("bill").password(passwordEncoder().encode("12345"))
                .authorities("read")
                .build();

        uds.createUser(u1);

        var u2 = User.withUsername("jane").password(passwordEncoder().encode("12345"))
                .authorities("write")
                .build();

        uds.createUser(u2);

        return uds;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
