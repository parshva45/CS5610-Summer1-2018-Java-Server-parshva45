package com.example.webdev1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev1.models.BaseExamQuestion;
import com.example.webdev1.models.Exam;
import com.example.webdev1.repositories.ExamRepository;

@RestController
public class ExamQuestionService {
	
	@Autowired
	ExamRepository examRepository;
	
	@GetMapping("/api/exam/{examId}/question")
	public List<BaseExamQuestion> getQuestionByExamId(@PathVariable("examId") int examId){
		Optional<Exam> data = examRepository.findById(examId);
		 if(data.isPresent()) {
			 Exam exam = data.get();
			 return exam.getQuestions();
		 }
		 return null;
	}
}