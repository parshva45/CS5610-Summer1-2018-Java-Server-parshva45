package com.example.webdev1.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JOINED_MULTIPLE_CHOICE_QUESTION")
public class MultipleChoiceExamQuestion
	extends BaseExamQuestion {

	private String options;
	private int correctOption;
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public int getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}
}