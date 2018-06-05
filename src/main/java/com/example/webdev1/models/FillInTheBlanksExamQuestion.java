package com.example.webdev1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JOINED_TRUE_OR_FALSE_QUESTION")
public class FillInTheBlanksExamQuestion
	extends BaseExamQuestion {
	@Column(name = "VARIABLES", nullable = false)
	private String variables;
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
}