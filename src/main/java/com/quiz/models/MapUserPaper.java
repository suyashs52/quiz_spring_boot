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
@Table(name = "map_user_paper")
public class MapUserPaper {

	@Id
	@GeneratedValue(generator = "map_user_paper_generator")
	@SequenceGenerator(name = "map_user_paper_generator", sequenceName = "map_user_paper_sequence", initialValue = 1000)
	private Integer id;

	@NotNull
	@Column(name = "fk_user")
	private Integer fkUser;
 

	@Column(name = "fk_paper")
	private Integer fkPaper;

	@Column(name = "marks")
	private Integer marks;

	public MapUserPaper(Integer userid, Integer paperid) {
		// TODO Auto-generated constructor stub
		this.fkUser = userid;
		this.fkPaper = paperid;
	}

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
 

	public Integer getFkPaper() {
		return fkPaper;
	}

	public void setFkPaper(Integer fkPaper) {
		this.fkPaper = fkPaper;
	}

	public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

}
