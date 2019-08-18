package com.quiz.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.quiz.validator.ValidRole;

@Entity
@Table(name = "option")
public class Option {
	@Id
	@GeneratedValue(generator = "option_generator")
	@SequenceGenerator(name = "option_generator", sequenceName = "option_sequence", initialValue = 1000)
	private Integer id;

	 
	@NotNull
	@Column(name = "fk_question_id")
	private Integer fkQuestionId;

	@Column(name = "description")
	private String description;

	@Column(name = "is_correct_choice")
	private Boolean isCorrectChoice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFkQuestionId() {
		return fkQuestionId;
	}

	public void setFkQuestionId(Integer fkQuestionId) {
		this.fkQuestionId = fkQuestionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsCorrectChoice() {
		return isCorrectChoice;
	}

	public void setIsCorrectChoice(Boolean isCorrectChoice) {
		this.isCorrectChoice = isCorrectChoice;
	}
 
	
	
 

}
