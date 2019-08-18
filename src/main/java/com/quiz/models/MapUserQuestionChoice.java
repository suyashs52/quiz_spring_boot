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
@Table(name = "map_user_question_choice")
public class MapUserQuestionChoice {


	@Id
	@GeneratedValue(generator = "map_user_question_choice_generator")
	@SequenceGenerator(name = "map_user_question_choice_generator", sequenceName = "map_user_question_choice_sequence", initialValue = 1000)
	private Integer id;

	 
	@NotNull
	@Column(name = "fk_user")
	private Integer fkUser;

	@NotNull
	@Column(name = "fk_paper")
	private Integer fkPaper;

	@NotNull
	@Column(name = "fk_question")
	private Integer fkQuestion;


	@NotNull
	@Column(name = "fk_choice")
	private Integer fkChoice;

	 
	@Column(name = "is_correct")
	private Boolean isCorrect;
	
	@Column(name = "marks")
	private Integer marks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFkUser() {
		return fkUser;
	}

	public void setFkUser(Integer fkUser) {
		this.fkUser = fkUser;
	}

	public Integer getFkQuestion() {
		return fkQuestion;
	}

	public void setFkQuestion(Integer fkQuestion) {
		this.fkQuestion = fkQuestion;
	}

	public Integer getFkChoice() {
		return fkChoice;
	}

	public void setFkChoice(Integer fkChoice) {
		this.fkChoice = fkChoice;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public Integer getFkPaper() {
		return fkPaper;
	}

	public void setFkPaper(Integer fkPaper) {
		this.fkPaper = fkPaper;
	}
	
	
 
}
