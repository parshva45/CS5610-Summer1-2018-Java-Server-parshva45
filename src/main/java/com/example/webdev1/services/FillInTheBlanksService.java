package com.example.webdev1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev1.models.Exam;
import com.example.webdev1.models.FillInTheBlanksExamQuestion;
import com.example.webdev1.repositories.ExamRepository;
import com.example.webdev1.repositories.FillInTheBlanksExamQuestionRepository;

@RestController
public class FillInTheBlanksService {
	
	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private FillInTheBlanksExamQuestionRepository fibRepository;
	
	@PostMapping("/api/exam/{examId}/fillintheblanks")
	public FillInTheBlanksExamQuestion addQuestionByExamId(@PathVariable("examId") int examId,
			@RequestBody FillInTheBlanksExamQuestion fib) {
		 Optional<Exam> data = examRepository.findById(examId);
		 if(data.isPresent()) {
			 Exam exam = data.get();
			 fib.setExam(exam);
			 return fibRepository.save(fib);
		 }
		return null;
	}
	
	@PutMapping("/api/question/{questionId}/fillintheblanks")
	public FillInTheBlanksExamQuestion updateQuestion(@PathVariable("questionId") int questionId,
			@RequestBody FillInTheBlanksExamQuestion newFib) {
		Optional<FillInTheBlanksExamQuestion> data = fibRepository.findById(questionId);
		if(data.isPresent()) {
			FillInTheBlanksExamQuestion fib = data.get();
			fib.setTitle(newFib.getTitle());
			fib.setSubtitle(newFib.getSubtitle());
			fib.setDescription(newFib.getDescription());
			fib.setPoints(newFib.getPoints());
			fib.setVariables(newFib.getVariables());
			fib.setAnswers(newFib.getAnswers());
			fibRepository.save(fib);
			return fib;
		}
		return null;
	}
	
}