package com.example.webdev1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.EssayExamQuestion;

public interface EssayExamQuestionRepository
	extends CrudRepository<EssayExamQuestion, Integer> {

}
