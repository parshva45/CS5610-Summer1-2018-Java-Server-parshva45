package com.example.webdev1.repositories.exam.joined;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.exam.joined.BaseQuestionJoined;

public interface BaseQuestionRepositoryJoined
	extends CrudRepository<BaseQuestionJoined, Integer>{
}