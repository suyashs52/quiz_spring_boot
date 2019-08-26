package com.quiz.projection;

import org.springframework.beans.factory.annotation.Value;

public interface UserProjection {
	@Value("#{target.username}")
	String getUsername();

	@Value("#{target.id}")
	Integer getId();

	@Value("#{target.name}")
	String getName();

	@Value("#{target.phone}")
	String getPhone();

	@Value("#{target.role}")
	String getRole();
}
