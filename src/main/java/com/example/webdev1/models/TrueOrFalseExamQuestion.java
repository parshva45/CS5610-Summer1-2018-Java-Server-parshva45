package com.example.webdev1.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TRUE_OR_FALSE_QUESTION")
public class TrueOrFalseExamQuestion
	extends BaseExamQuestion {

	private Boolean isTrue;
	
	public Boolean getIsTrue() {
		return isTrue;
	}
	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}
}