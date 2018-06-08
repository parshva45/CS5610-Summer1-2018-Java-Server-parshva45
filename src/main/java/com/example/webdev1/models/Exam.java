package com.example.webdev1.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Exam extends Widget {
	private String title;
	@OneToMany(mappedBy="exam",cascade=CascadeType.REMOVE,orphanRemoval=true)
	private List<BaseExamQuestion> questions;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<BaseExamQuestion> getQuestions() {
		return questions;
	}
	public void setQuestions(List<BaseExamQuestion> questions) {
		this.questions = questions;
	}
}