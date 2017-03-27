package com.sopra.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sopra.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

		
	 User findUserByName(String name); 
}
