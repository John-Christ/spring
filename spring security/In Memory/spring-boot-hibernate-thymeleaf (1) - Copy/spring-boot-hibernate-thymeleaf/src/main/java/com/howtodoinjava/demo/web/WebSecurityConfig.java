package com.howtodoinjava.demo.web;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user1 = User
                .withUsername("john")
                .password("$2a$10$sWszOXuTlN0amQi8vXp4cerb.tJUQo.4FzLAnTCsSqChsYhlLdQWW")
                .roles("USER")
                .build();
        UserDetails user2 = User
                .withUsername("admin")
                .password("$2a$10$PH0p2x2x8oi5bKx.80Bt7ubMAiHdZnqm9TC/Cpss9VoccyTYw1AoC")
                .roles("ADMIN")
                .build();
        UserDetails user3 = User
                .withUsername("laroma")
                .password("$2a$10$zSatHYUP4qZYRzkHe9ua..mIXq5rGdWa0zMfkaPQLj99eom.WQO6S")
                .roles("EDITOR")
                .build();



        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/").permitAll()
                .mvcMatchers("/edit").hasRole("ADMIN")
               // .antMatchers("/edit").hasAnyAuthority("ADMIN", "EDITOR")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("u").passwordParameter("p")
                .permitAll()
                .defaultSuccessUrl("/list-employees")
                .and()
                .logout().permitAll();
    }

}
