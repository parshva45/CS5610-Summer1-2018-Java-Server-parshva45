package com.example.webdev1.repositories.exam.joined;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.exam.joined.TrueOrFalseQuestionJoined;

public interface TrueOrFalseQuestionRepositoryJoined
	extends CrudRepository<TrueOrFalseQuestionJoined, Integer>{}