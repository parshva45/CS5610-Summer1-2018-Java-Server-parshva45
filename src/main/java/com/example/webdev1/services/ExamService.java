package com.example.webdev1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev1.models.Exam;
import com.example.webdev1.models.MultipleChoiceExamQuestion;
import com.example.webdev1.models.BaseExamQuestion;
import com.example.webdev1.models.TrueOrFalseExamQuestion;
import com.example.webdev1.repositories.ExamRepository;
import com.example.webdev1.repositories.MultipleChoiceExamQuestionRepository;
import com.example.webdev1.repositories.TrueOrFalseExamQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueOrFalseExamQuestionRepository trueFalseRepository;
	@Autowired
	MultipleChoiceExamQuestionRepository mutiRepo;

	@GetMapping("/api/multi/{questionId}")
	public MultipleChoiceExamQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceExamQuestion> optional = mutiRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@GetMapping("/api/truefalse/{questionId}")
	public TrueOrFalseExamQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueOrFalseExamQuestion> optional = trueFalseRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam/{examId}/question")
	public List<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<BaseExamQuestion> questions = exam.getQuestions();
			return questions;
		}
		return null;
	}
}