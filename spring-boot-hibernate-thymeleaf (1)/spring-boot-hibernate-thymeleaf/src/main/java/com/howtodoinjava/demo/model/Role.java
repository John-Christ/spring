package com.howtodoinjava.demo.model;

import javax.persistence.*;

import com.howtodoinjava.demo.model.EmployeeEntity;

@Entity
@Table(name="Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // private String name;
    private String email;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /* public String getName() {
        return name;
    } */


    public String getEmail() {
        return email;
    }

   /* public void setName(String name) {
        this.name = name;
    }  */

    public void setEmail(String email) {
        this.email = email;
    }

}