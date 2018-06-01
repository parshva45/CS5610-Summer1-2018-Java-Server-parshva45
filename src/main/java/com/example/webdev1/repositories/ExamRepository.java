package com.example.webdev1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.Exam;

public interface ExamRepository
extends CrudRepository<Exam, Integer>{

}