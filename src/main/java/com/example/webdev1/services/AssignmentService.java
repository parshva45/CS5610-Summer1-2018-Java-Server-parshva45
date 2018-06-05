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

import com.example.webdev1.models.Assignment;
import com.example.webdev1.models.Lesson;
import com.example.webdev1.models.Widget;
import com.example.webdev1.repositories.AssignmentRepository;
import com.example.webdev1.repositories.LessonRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Autowired
	private LessonRepository lessonRepository;
		
	@GetMapping("api/assignment")
	public List<Assignment> findAllAssignments(){
		return (List<Assignment>) assignmentRepository.findAll();
	}
	
	@GetMapping("api/lesson/{lessonId}/assignment")
	public List<Assignment> findAllAssignmentsForLesson(@PathVariable("lessonId") int lessonId){
		 Optional<Lesson> data = lessonRepository.findById(lessonId);
		 List<Assignment> assignment = new ArrayList<>();
		 if(data.isPresent()) {
			 Lesson lesson = data.get();
			 List<Widget> widgets = lesson.getWidgets();
			 Iterator<Widget> it = widgets.iterator();
			 while(it.hasNext()) {
				 Widget widgetId = it.next();
				 if(widgetId.getWidgetType().equals("Assignment")) {
					 assignment.add((Assignment) widgetId);
				 }
			 }
			 return assignment;
		 }
		 return null;
	}
	
	@PostMapping("api/lesson/{lessonId}/assignment")
	public Assignment addAssignment(@PathVariable("lessonId") int lessonId, 
			@RequestBody Assignment assignmentWidget) {
		 Optional<Lesson> data = lessonRepository.findById(lessonId);
		 if(data.isPresent()) {
			 Lesson lesson = data.get();
			 assignmentWidget.setLesson(lesson);
			 return assignmentRepository.save(assignmentWidget);
		 }
		 return null;
	}
	
	@DeleteMapping("api/assignment/{assignmentId}")
	public void deleteAssignment(@PathVariable("assignmentId") int assignmentId) {
		assignmentRepository.deleteById(assignmentId);
	}
	
}