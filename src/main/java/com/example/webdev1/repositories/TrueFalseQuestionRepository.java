package com.example.webdev1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.TrueFalseQuestion;

public interface TrueFalseQuestionRepository
	extends CrudRepository<TrueFalseQuestion, Integer> {
	
}