package com.example.webdev1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Integer>{

}
