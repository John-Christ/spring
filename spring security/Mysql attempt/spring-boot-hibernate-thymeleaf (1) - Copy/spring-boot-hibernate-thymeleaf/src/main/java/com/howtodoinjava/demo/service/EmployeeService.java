package com.howtodoinjava.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.repository.EmployeeRepository;
import com.howtodoinjava.demo.model.EmployeeEntity;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;


	@Autowired
	private PasswordEncoder passwordEncoder;

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



	public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException  {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		EmployeeEntity user = repository.findByEmail(entity.getEmail());

		if(user != null) {

			throw new RecordNotFoundException(" employee record exist for given email");

		}

		if (entity.getId() == null) {
			entity.setPassword(encoder.encode(entity.getPassword()));
			entity = repository.save(entity);
		//	entity = repository.save(entity.getPassword());

			return entity;
		} else {
			Optional<EmployeeEntity> employee = repository.findById(entity.getId());

			if (employee.isPresent()) {
				EmployeeEntity newEntity = employee.get();
				newEntity.setEmail(entity.getEmail());
				newEntity.setPhone(entity.getPhone());
				newEntity.setFirstName(entity.getFirstName());
				newEntity.setLastName(entity.getLastName());
                newEntity.setPassword(encoder.encode(entity.getPassword()));

				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);

				return entity;
			}
		}
	}




	/*
	public EmployeeEntity display (EmployeeEntity entity) {
		Optional<EmployeeEntity> employee = repository.findById(entity.getId());

		if (employee.isPresent()) {
			EmployeeEntity newEntity = employee.get();
			newEntity.setEmail(entity.getEmail());
			newEntity.setPhone(entity.getPhone());
			newEntity.setFirstName(entity.getFirstName());
			newEntity.setLastName(entity.getLastName());


			newEntity = repository.save(newEntity);

			return newEntity;
		} else {
			entity = repository.save(entity);

			return entity;
		}

	}
	 */



	
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
}



