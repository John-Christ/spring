package com.howtodoinjava.demo.web;

import java.util.List;
import java.util.Optional;

import com.howtodoinjava.demo.model.EmailExistsConstraint;
import com.howtodoinjava.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//import binder for validation purpose
import org.springframework.web.bind.WebDataBinder;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import javax.validation.Valid;
import javax.validation.constraints.Null;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/")
public class EmployeeMvcController implements WebMvcConfigurer {

    @Autowired
    EmployeeService service;

    @Autowired
    EmployeeRepository repository;


    @RequestMapping(path="login")
    public String login(){
        return "login";
    }

    // Login form




    @RequestMapping(path = "/")
    public String getAllEmployees(Model model) {
        List<EmployeeEntity> list = service.getAllEmployees();

        model.addAttribute("employees", list);
        return "list-employees";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException {
        if (id.isPresent()) {
            EmployeeEntity entity = service.getEmployeeById(id.get());
            model.addAttribute("employee", entity);
        } else {
            model.addAttribute("employee", new EmployeeEntity());
        }
        return "add-edit-employee";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id, EmployeeEntity employee)
            throws RecordNotFoundException {

        service.deleteEmployeeById(id);
        return "redirect:/list-employees";

    }


    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    public String createOrUpdateEmployee(@Valid EmployeeEntity employee, BindingResult bindingResult, Model model )  {


  /*      EmployeeEntity user = repository.findByEmail(employee.getEmail());
       // String response = "A user with the same email exists already!";


        if(user != null) {
                model.addAttribute("createOrUpdateEmployee", true);
            return "add-edit-employee";

        }   */



         if (bindingResult.hasErrors()) {
            model.addAttribute("org.springframework.validation.BindingResult.employee", bindingResult);
             return "add-edit-employee";
        }


        else {
            service.createOrUpdateEmployee(employee);
            return "redirect:/list-employees";
        }
    }






    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/employee")
    public String employee() {
        return "employee";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }






/*

    public void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login-error")
                .defaultSuccessUrl("/list-employees")
                .and()
                .logout()

                .logoutSuccessUrl("/login");


    }




    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }



 */


}









