package com.howtodoinjava.demo.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.Valid;

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
   @Column(name="email", nullable=false, length=200)
    @EmailExistsConstraint(message="This email exists already. Login instead")
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
        return "EmployeeEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email   + ", phone=" + phone +", password="+password+" ]";
    }
}