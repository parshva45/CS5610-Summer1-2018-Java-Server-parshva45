package com.example.webdev1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.Hello;

public interface HelloRepository extends CrudRepository<Hello, Integer> {

}
