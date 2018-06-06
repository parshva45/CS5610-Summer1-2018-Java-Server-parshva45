package com.example.webdev1.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FILL_IN_THE_BLANKS_QUESTION")
public class FillInTheBlanksExamQuestion
	extends BaseExamQuestion {

	private String variables;
	private String values;
	
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
}