package com.example.webdev1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.TrueOrFalseExamQuestion;

public interface TrueOrFalseExamQuestionRepository
	extends CrudRepository<TrueOrFalseExamQuestion, Integer> {
	
}