package com.howtodoinjava.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.howtodoinjava.demo.model.EmployeeEntity;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {}
