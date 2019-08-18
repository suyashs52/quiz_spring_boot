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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "paper")
public class Paper {

	@Id
	@GeneratedValue(generator = "paper_generator")
	@SequenceGenerator(name = "paper_generator", sequenceName = "paper_sequence", initialValue = 1000)
	private Integer id;

	@NotBlank
	@Size(min = 3, max = 100)
	private String name;

	@NotBlank
	@Size(min = 3, max = 200)
	@Column(name = "description", nullable = false, updatable = false)
	private String desc;

	@NotNull
	@Min(2)
	@Max(60)
	@NumberFormat(style = Style.NUMBER)
	@Column(name = "total_time", nullable = false, updatable = false)

	private Integer totalTime;

	@NotNull
	@Min(10)
	@Max(20)
	@NumberFormat(style = Style.NUMBER)
	@Column(name = "total_question", nullable = false, updatable = false)

	private Integer totalQuestion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date createDate;

	@Size(min = 3, max = 100)
	@Column(name = "created_by")
	private String createdBy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

	public Integer getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(Integer totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

}
