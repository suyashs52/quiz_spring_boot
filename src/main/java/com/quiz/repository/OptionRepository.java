package com.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.models.Option;

public interface OptionRepository extends JpaRepository<Option, Integer> {
	
	List<Option> findAllByFkQuestionIdIn(List<Integer> fkQuestionId);
}