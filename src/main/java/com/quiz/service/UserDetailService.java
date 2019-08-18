package com.quiz.service;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.quiz.models.User;
import com.quiz.utils.ResourceNotFoundException;

public interface UserDetailService {
	Map<String, Object> findAll(Pageable pageable);

	Optional<User> findById(Integer itemId);

	User saveUser(@Valid User user) throws ResourceNotFoundException;

	void updateUser(@Valid User user) throws ResourceNotFoundException;

	void delete(User user);

}
