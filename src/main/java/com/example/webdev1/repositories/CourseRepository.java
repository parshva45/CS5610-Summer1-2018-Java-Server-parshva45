package com.example.webdev1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {

}