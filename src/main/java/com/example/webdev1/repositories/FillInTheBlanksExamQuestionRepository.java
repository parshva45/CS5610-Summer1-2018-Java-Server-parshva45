package com.example.webdev1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.FillInTheBlanksExamQuestion;

public interface FillInTheBlanksExamQuestionRepository
	extends CrudRepository<FillInTheBlanksExamQuestion, Integer>{

}