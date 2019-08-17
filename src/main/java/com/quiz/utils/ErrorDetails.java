package com.quiz.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

public class ErrorDetails {
	private final Date timestamp;
	private final String message;
	private final String details;
	private final HttpStatus status;
	private List<String> errors = new ArrayList<>();

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public ErrorDetails(Date timestamp, String message, String details, HttpStatus status) {
		super();

		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.status = status;

	}

	public ErrorDetails(Date timestamp, String message, String details, HttpStatus status, List<String> errors) {
		super();

		if (errors.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (String s : errors) {
				sb.append(s);
				sb.append(", ");
			}
			 
			this.message = sb.toString().substring(0, sb.lastIndexOf(","));
		} else {
			this.message = message;
		}
		this.timestamp = timestamp;

		this.details = details;
		this.status = status;
		this.errors = errors;

	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public HttpStatus getStatus() {
		return this.status;
	}

	public void addFieldError(String field, String defaultMessage) {
		// TODO Auto-generated method stub

	}
}
