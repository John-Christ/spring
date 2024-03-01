package com.howtodoinjava.demo.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.validation.annotation.Validated;

//import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.Valid;
import java.lang.annotation.Native;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

/*
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

 */


@Valid
@Entity
@Table(name="TBL_EMPLOYEES")
public class EmployeeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	@NotNull
    @Column(name="first_name")
    private String firstName;
    
   @NotBlank(message="Your Last name is required!")
   @Size(min=2, max=30)
    @Column(name="last_name")
    private String lastName;
    
   @Email(message="Please, enter a valid email")
   @NotBlank(message="Your email is required!")
  // @UniqueElements(message="unique element")
   @EmailExistsConstraint(message = "A user already exists with the same email!")
   //@UniqueConstraint(message="unique constraint")
   @Column(name="email", nullable=false, length=200)
    private String email;



    @NotBlank(message="Phone is required!")
    @Column(name="phone")
	private String phone;


	@NotBlank(message="Password is required!")
	@Column(name="password")
	private String password;




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	//Add getMeth
	public String getPhone () { return phone;}


	//Add setMeth
	public void setPhone(String phone) {this.phone = phone; }


	public String getPassword () { return password;}


	//Add setMeth
	public void setPassword(String password) {this.password = password; }


	@Override
    public String toString() {
        return "EmployeeEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email   + ", phone=" + phone +", password="+password+", roles=" + roles +" ]";
    }



	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Role> roles;


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


}