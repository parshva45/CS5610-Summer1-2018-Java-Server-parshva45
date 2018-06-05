package com.example.webdev1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.MultipleChoiceExamQuestion;

public interface MultipleChoiceExamQuestionRepository
	extends CrudRepository<MultipleChoiceExamQuestion, Integer> {
	
}