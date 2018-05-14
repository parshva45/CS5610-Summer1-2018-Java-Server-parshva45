package com.example.webdev1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev1.repositories.UserRepository;

import com.example.webdev1.models.User;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user) {
		List<User> userList = findUserByCredentials(user.getUsername(), user.getPassword());
		return (!userList.isEmpty()) ? userList.get(0) : null;
	}
	
	public List<User> findUserByCredentials(String username, String password){
		return (List<User>) repository.findUserByCredentials(username, password);
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user) {
		List<User> userList = findUserByUsername(user.getUsername());
		return (userList.isEmpty()) ? createUser(user) : null;
	}
	
	public List<User> findUserByUsername(String username){
		return (List<User>) repository.findUserByUsername(username);
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}
	
	@PutMapping("/api/user")
	public User updateUser(@RequestBody User newUser) {
		List<User> data = findUserByUsername(newUser.getUsername());
		if(!data.isEmpty()) {
			User user = data.get(0);
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setRole(newUser.getRole());
			repository.save(user);
			return user;
		}
		return null;
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
}