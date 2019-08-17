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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.quiz.validator.ValidRole;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "phone" }) })
// @JsonIgnoreProperties(value = { "createDate", "updatedAt" }, allowGetters =
// true)
public class User extends Login {
	@Id
	@GeneratedValue(generator = "user_generator")
	@SequenceGenerator(name = "user_generator", sequenceName = "user_sequence", initialValue = 1000)
	private Long id;

	@NotBlank
	@Size(min = 3, max = 100)
	private String name;

	@NaturalId
	@NotBlank
	@Size(min = 10, max = 10)
	@NumberFormat(style = Style.NUMBER)
	private String phone;

	@ValidRole(message = "role must be admin / user", value = { Roles.ADMIN, Roles.USER })
	private String role;

	@Temporal(TemporalType.TIMESTAMP)
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	@Column(name = "created_date", nullable = false, updatable = false)

	// @Convert(converter = LocalDateTimeConverter.class)

	private Date createDate;

	@Size(min = 3, max = 100)
	@Column(name = "created_by")
	private String createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

}
