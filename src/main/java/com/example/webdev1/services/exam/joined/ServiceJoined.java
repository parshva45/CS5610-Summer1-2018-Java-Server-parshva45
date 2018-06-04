package com.example.webdev1.services.exam.joined;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev1.models.exam.joined.BaseQuestionJoined;
import com.example.webdev1.models.exam.joined.FillInTheBlankQuestionJoined;
import com.example.webdev1.models.exam.joined.TrueOrFalseQuestionJoined;
import com.example.webdev1.repositories.exam.joined.BaseQuestionRepositoryJoined;
import com.example.webdev1.repositories.exam.joined.FillInTheBlankQuestionRepositoryJoined;
import com.example.webdev1.repositories.exam.joined.TrueOrFalseQuestionRepositoryJoined;

@RestController
public class ServiceJoined {
	@Autowired
	BaseQuestionRepositoryJoined baseRepo;
	@Autowired
	FillInTheBlankQuestionRepositoryJoined fillRepo;
	@Autowired
	TrueOrFalseQuestionRepositoryJoined trueRepo;
	
	@GetMapping("/api/inheritance/joined/base")
	public BaseQuestionJoined createBaseQuestion() {
		BaseQuestionJoined q = new BaseQuestionJoined();
		q.setDescription("descriptions 123");
		q.setInstructions("instructions 123");
		q.setPoints(123);
		q.setTitle("title 123");
		return baseRepo.save(q);
	}
	@GetMapping("/api/inheritance/joined/fill")
	public FillInTheBlankQuestionJoined createFillQuestion() {
		FillInTheBlankQuestionJoined q = new FillInTheBlankQuestionJoined();
		q.setDescription("descriptions 234");
		q.setInstructions("instructions 234");
		q.setPoints(234);
		q.setTitle("title 234");
		q.setVariables("variables 234");
		return fillRepo.save(q);
	}
	@GetMapping("/api/inheritance/joined/true")
	public TrueOrFalseQuestionJoined createTrueQuestion() {
		TrueOrFalseQuestionJoined q = new TrueOrFalseQuestionJoined();
		q.setDescription("descriptions 345");
		q.setInstructions("instructions 345");
		q.setPoints(345);
		q.setTitle("title 345");
		q.setIsTrue(true);
		return trueRepo.save(q);
	}
}