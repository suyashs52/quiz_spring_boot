package com.quiz.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.quiz.models.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByPhone(String phone);
	
	public Page<User> findAll(Pageable pageable);
}
