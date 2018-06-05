package com.example.webdev1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.Assignment;

public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {

}