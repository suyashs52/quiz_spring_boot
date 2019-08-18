package com.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	List<Question> findAllByFkPaperId(Integer fkPaperId);
}