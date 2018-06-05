package com.example.webdev1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev1.models.BaseExamQuestion;
import com.example.webdev1.repositories.BaseExamQuestionRepository;

@RestController
public class ExamQuestionService {
	@Autowired
	BaseExamQuestionRepository baseRepo;
	
	@GetMapping("/api/inheritance/joined/base")
	public BaseExamQuestion createBaseQuestion() {
		BaseExamQuestion q = new BaseExamQuestion();
		q.setDescription("descriptions 123");
		q.setInstructions("instructions 123");
		q.setPoints(123);
		q.setTitle("title 123");
		return baseRepo.save(q);
	}
}