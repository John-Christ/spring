package com.howtodoinjava.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.repository.EmployeeRepository;

import javax.management.relation.Role;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Service
public class EmployeeService  {

	@Autowired
	EmployeeRepository repository;

	public List<EmployeeEntity> getAllEmployees() {
		List<EmployeeEntity> result = (List<EmployeeEntity>) repository.findAll();

		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<EmployeeEntity>();
		}
	}

	public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
		Optional<EmployeeEntity> employee = repository.findById(id);

		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

/*
	public EmployeeEntity findUserByEmail(String email) {
		return Repository.findByEmail(email);
	}
*/
	/*
	public EmployeeEntity getEmployeeByEmail(EmployeeEntity entity) // {

			throw new RecordNotFoundException("An employee with this email exist already ");

		} */



	public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity)  {

		/* EmployeeEntity user = repository.findByEmail(entity.getEmail());

		if(user != null) {

			throw new RecordNotFoundException(" employee record exist for given email");

		} */


		if (entity.getId() == null ) {
			entity = repository.save(entity);

			return entity;
		}

		/* if (entity.getEmail() != null) {
			entity = repository.findByEmail(entity.getEmail());
			return entity;

			}
		}    */

		else {
			Optional<EmployeeEntity> employee = repository.findById(entity.getId()) ;

			if (employee.isPresent()) {
				EmployeeEntity newEntity = employee.get();
				newEntity.setEmail(entity.getEmail());
				newEntity.setPhone(entity.getPhone());
				newEntity.setFirstName(entity.getFirstName());
				newEntity.setLastName(entity.getLastName());
                newEntity.setPassword(entity.getPassword());

				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);

				return entity;
			}
		}
	}




	public void deleteEmployeeById(Long id) throws RecordNotFoundException 
	{
		Optional<EmployeeEntity> employee = repository.findById(id);
		
		if(employee.isPresent()) 
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}


/*
	public UserDetails loadUserByEmail(String email)throws RecordNotFoundException{
		EmployeeEntity user = repository.findByEmail(email);
		if(user==null){
			throw new RecordNotFoundException("User not exists by this Email");
		}

		Set<GrantedAuthority> authorities = user.getRoles().stream()
				.map((role) -> new SimpleGrantedAuthority(user.getEmail()))
				.collect(Collectors.toSet());

		return new org.springframework.security.core.userdetails.User(email,user.getPassword(),authorities);
	}
*/

}






