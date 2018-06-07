package com.example.webdev1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev1.models.Exam;
import com.example.webdev1.models.TrueOrFalseExamQuestion;
import com.example.webdev1.repositories.ExamRepository;
import com.example.webdev1.repositories.TrueOrFalseExamQuestionRepository;

@RestController
public class TrueOrFalseService {

	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private TrueOrFalseExamQuestionRepository tfRepository;
	
	@PostMapping("/api/exam/{examId}/truefalse")
	public TrueOrFalseExamQuestion addQuestionByExamId(@PathVariable("examId") int examId,
			@RequestBody TrueOrFalseExamQuestion tfQuestion) {
		 Optional<Exam> data = examRepository.findById(examId);
		 if(data.isPresent()) {
			 Exam exam = data.get();
			 tfQuestion.setExam(exam);
			 return tfRepository.save(tfQuestion);
		 }
		return null;
	}
	
	@PutMapping("/api/question/{questionId}/truefalse")
	public TrueOrFalseExamQuestion updateQuestion(@PathVariable("questionId") int questionId,
			@RequestBody TrueOrFalseExamQuestion newTF) {
		Optional<TrueOrFalseExamQuestion> data=tfRepository.findById(questionId);
		if(data.isPresent()) {
			TrueOrFalseExamQuestion tf=data.get();
			tf.setTitle(newTF.getTitle());
			tf.setSubtitle(newTF.getSubtitle());
			tf.setDescription(newTF.getDescription());
			tf.setPoints(newTF.getPoints());
			tf.setIsTrue(newTF.getIsTrue());
			tfRepository.save(tf);
			return tf;
		}
		return null;
	}
	
}