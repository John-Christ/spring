package com.howtodoinjava.demo.web;

import java.util.List;
import java.util.Optional;

import com.howtodoinjava.demo.model.EmailExistsConstraint;
import com.howtodoinjava.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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


@Controller
@RequestMapping("/")
public class EmployeeMvcController implements WebMvcConfigurer {

    @Autowired
    EmployeeService service;


    @Autowired
    EmployeeRepository repository;

    @RequestMapping
    public String home(){
        return "home";
    }


    @RequestMapping(path = "/login")
    public String login(){
        return "login";
    }


    @RequestMapping(path = "/list-employees")
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
    public String createOrUpdateEmployee(@Valid EmployeeEntity employee, BindingResult bindingResult, Model model) throws RecordNotFoundException  {

     /*   EmployeeEntity user = repository.findByEmail(employee.getEmail());


        if (user != null) {
            model.addAttribute("user", user);

            return "add-edit-employee";
        }  */

         if (bindingResult.hasErrors()) {
            model.addAttribute("org.springframework.validation.BindingResult.employee", bindingResult);
            return "add-edit-employee";
        }

        else {
            service.createOrUpdateEmployee(employee);
            return "redirect:/list-employees";
        }
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*
    @RequestMapping(path = "/display/{id}")
    public String displayEmployeeById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException {
        if (id.isPresent()) {
            EmployeeEntity entity = service.getEmployeeById(id.get());
            model.addAttribute("employee", entity);
        }
        service.getEmployeeById(id.get());
        return "redirect:/";
    }

 */



}





