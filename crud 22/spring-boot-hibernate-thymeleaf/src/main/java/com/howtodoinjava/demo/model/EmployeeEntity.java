  package com.howtodoinjava.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

  @Entity
  public class User {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;

	  @NotBlank(message = "Name is mandatory")
	  private String name;

	  @NotBlank(message = "Email is mandatory")
	  private String email;

	  // standard constructors / setters / getters / toString
  }