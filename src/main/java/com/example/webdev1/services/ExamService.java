package com.example.webdev1.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev1.models.Exam;
import com.example.webdev1.models.Lesson;
import com.example.webdev1.models.Widget;
import com.example.webdev1.repositories.ExamRepository;
import com.example.webdev1.repositories.LessonRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@PostMapping("/api/lesson/{lessonId}/exam")
	public Exam addExam(@PathVariable("lessonId") int lessonId,
			@RequestBody Exam exam) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		 if(data.isPresent()) {
			 Lesson lesson = data.get();
			 exam.setLesson(lesson);
			 return examRepository.save(exam);
		 }
		 return null;
	}
	
	@GetMapping("/api/lesson/{lessonId}/exam")
	public List<Exam> findAllExamsForLesson(@PathVariable("lessonId") int lessonId){
		 Optional<Lesson> data = lessonRepository.findById(lessonId);
		 List<Exam> exams=new ArrayList<>();
		 if(data.isPresent()) {
			 Lesson lesson = data.get();
			 List<Widget> widgets = lesson.getWidgets();
			 Iterator<Widget> it = widgets.iterator();
			 while(it.hasNext()) {
				 Widget widget = it.next();
				 if(widget.getWidgetType().equals("Exam")) {
					 Optional<Exam> exam = examRepository.findById(widget.getId());
					 if(exam.isPresent()) {
						 exams.add(exam.get());
					 }
				 }
			 }
			 return exams;
		 }
		 return null;
	}
	
	@GetMapping("/api/exam")
	public List<Exam> findAllExams(){
		return (List<Exam>) examRepository.findAll();
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int examId) {
		examRepository.deleteById(examId);
	}
}