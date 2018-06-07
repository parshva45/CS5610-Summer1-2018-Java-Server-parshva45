package com.example.webdev1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev1.models.EssayExamQuestion;
import com.example.webdev1.models.Exam;
import com.example.webdev1.repositories.EssayExamQuestionRepository;
import com.example.webdev1.repositories.ExamRepository;

@RestController
public class EssayService {
	
	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private EssayExamQuestionRepository essayExamQuestionRepository;
	
	
	@PostMapping("/api/exam/{examId}/essay")
	public EssayExamQuestion addQuestionByExamId(@PathVariable("examId") int examId,
			@RequestBody EssayExamQuestion essayQuestion) {
		 Optional<Exam> data=examRepository.findById(examId);
		 if(data.isPresent()) {
			 Exam exam = data.get();
			 essayQuestion.setExam(exam);
			 return essayExamQuestionRepository.save(essayQuestion);
		 }
		return null;
	}
	
	@PutMapping("/api/question/{questionId}/essay")
	public EssayExamQuestion updateQuestion(@PathVariable("questionId") int questionId,
			@RequestBody EssayExamQuestion updatedEssay) {
		Optional<EssayExamQuestion> data=essayExamQuestionRepository.findById(questionId);
		if(data.isPresent()) {
			EssayExamQuestion essay=data.get();
			essay.setTitle(updatedEssay.getTitle());
			essay.setSubtitle(updatedEssay.getSubtitle());
			essay.setDescription(updatedEssay.getDescription());
			essay.setPoints(updatedEssay.getPoints());
			essayExamQuestionRepository.save(essay);
			return essay;
		}
		return null;
	}
}