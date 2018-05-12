package com.example.webdev1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.User;

public interface UserRepository
	extends CrudRepository<User, Integer>{

}