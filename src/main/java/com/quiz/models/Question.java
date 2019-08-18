package com.quiz.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "question")
public class Question {
	@Id
	@GeneratedValue(generator = "question_generator")
	@SequenceGenerator(name = "question_generator", sequenceName = "question_sequence", initialValue = 1000)
	private Integer id;

	@NotBlank
	@Size(min = 3, max = 100)
	private String question;

	@NotNull
	@Column(name = "fk_paper_id")
	private Integer fkPaperId;

	@Column(name = "correct_mark")
	private Integer correctMark;

	@Column(name = "wrongMark")
	private Integer wrongMark;

	@NotNull

	@Column(name = "fkLevel")
	private Integer fkLevel;

	@Column(name = "fk_correct_choice")
	private Integer fkCorrectChoice;
	@Transient
	List<Option> opt;
	@Transient
	MapUserQuestionChoice choices;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Integer getFkPaperId() {
		return fkPaperId;
	}

	public void setFkPaperId(Integer fkPaperId) {
		this.fkPaperId = fkPaperId;
	}

	public Integer getCorrectMark() {
		return correctMark;
	}

	public void setCorrectMark(Integer correctMark) {
		this.correctMark = correctMark;
	}

	public Integer getWrongMark() {
		return wrongMark;
	}

	public void setWrongMark(Integer wrongMark) {
		this.wrongMark = wrongMark;
	}

	
	
	
	 

	public Integer getFkCorrectChoice() {
		return fkCorrectChoice;
	}

	public void setFkCorrectChoice(Integer fkCorrectChoice) {
		this.fkCorrectChoice = fkCorrectChoice;
	}

	public Integer getFkLevel() {
		return fkLevel;
	}

	public void setFkLevel(Integer fkLevel) {
		this.fkLevel = fkLevel;
	}

	public List<Option> getOpt() {
		return opt;
	}

	public void setOpt(List<Option> opt) {
		this.opt = opt;
	}

	public MapUserQuestionChoice getChoices() {
		return choices;
	}

	public void setChoices(MapUserQuestionChoice choices) {
		this.choices = choices;
	}

}
