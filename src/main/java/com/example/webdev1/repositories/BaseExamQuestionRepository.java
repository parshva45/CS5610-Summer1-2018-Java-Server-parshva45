package com.example.webdev1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.BaseExamQuestion;

public interface BaseExamQuestionRepository
	extends CrudRepository<BaseExamQuestion, Integer>{
}