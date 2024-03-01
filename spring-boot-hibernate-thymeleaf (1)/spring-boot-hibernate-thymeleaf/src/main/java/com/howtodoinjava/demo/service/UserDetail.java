package com.howtodoinjava.demo.service;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.repository.EmployeeRepository;
@Service
public class UserDetail implements UserDetailsService {
    @Autowired
    EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException{
        EmployeeEntity user = repository.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("User not exists by Username");
        }
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getEmail()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(email,user.getPassword(),authorities);
    }
}
