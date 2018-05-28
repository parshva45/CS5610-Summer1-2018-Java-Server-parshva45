package com.example.webdev1.services;

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

import com.example.webdev1.models.Widget;
import com.example.webdev1.models.Lesson;
import com.example.webdev1.repositories.WidgetRepository;
import com.example.webdev1.repositories.LessonRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;

	@Autowired
	LessonRepository lessonRepository;
	
	@PostMapping("/api/lesson/{lessonId}/widget")
	public Widget createWidget(
			@PathVariable("lessonId") int lessonId,
			@RequestBody Widget newWidget) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		
		if(data.isPresent()) {
			Lesson lesson = data.get();
			newWidget.setLesson(lesson);
			return widgetRepository.save(newWidget);
		}
		return null;		
	}
	
	@GetMapping("/api/lesson/{lessonId}/widget")
	public List<Widget> findAllWidgetsForLesson(
			@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			return lesson.getWidgets();
		}
		return null;		
	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId)
	{
		widgetRepository.deleteById(widgetId);
	}
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets()
	{
		return (List<Widget>) widgetRepository.findAll();
	}
}