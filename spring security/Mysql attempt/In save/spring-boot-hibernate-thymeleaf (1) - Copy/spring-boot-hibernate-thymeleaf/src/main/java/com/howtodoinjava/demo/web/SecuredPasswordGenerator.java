package com.howtodoinjava.demo.web;

import com.howtodoinjava.demo.model.EmployeeEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecuredPasswordGenerator {

    public static void main(String[] args, EmployeeEntity entity) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = entity.getPassword();
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }

}