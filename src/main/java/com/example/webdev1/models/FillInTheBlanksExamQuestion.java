package com.example.webdev1.models;

import javax.persistence.Entity;

@Entity
public class FillInTheBlanksExamQuestion
	extends BaseExamQuestion {

	private String variables;
	private String answers;
	
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
}