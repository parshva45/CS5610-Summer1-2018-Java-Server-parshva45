package com.example.webdev1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev1.models.Exam;
import com.example.webdev1.models.MultipleChoiceExamQuestion;
import com.example.webdev1.repositories.ExamRepository;
import com.example.webdev1.repositories.MultipleChoiceExamQuestionRepository;

@RestController
public class MultipleChoiceService {
	
	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private MultipleChoiceExamQuestionRepository multipleChoiceRepository;
	
	@PostMapping("/api/exam/{examId}/choice")
	public MultipleChoiceExamQuestion addQuestionByExamId(@PathVariable("examId") int examId,
			@RequestBody MultipleChoiceExamQuestion mcq) {
		Optional<Exam> data=examRepository.findById(examId);
		 if(data.isPresent()) {
			 Exam exam = data.get();
			 mcq.setExam(exam);
			 return multipleChoiceRepository.save(mcq);
		 }
		return null;
	}
	
	@PutMapping("/api/question/{questionId}/choice")
	public MultipleChoiceExamQuestion updateQuestion(@PathVariable("questionId") int questionId,
			@RequestBody MultipleChoiceExamQuestion newMCQ) {
		Optional<MultipleChoiceExamQuestion> data = multipleChoiceRepository.findById(questionId);
		if(data.isPresent()) {
			MultipleChoiceExamQuestion mcq = data.get();
			mcq.setTitle(newMCQ.getTitle());
			mcq.setSubtitle(newMCQ.getSubtitle());
			mcq.setDescription(newMCQ.getDescription());
			mcq.setPoints(newMCQ.getPoints());
			mcq.setCorrectOption(newMCQ.getCorrectOption());
			mcq.setOptions(newMCQ.getOptions());
			multipleChoiceRepository.save(mcq);
			return mcq;
		}
		return null;
	}
}