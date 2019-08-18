package com.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.models.MapUserPaper;
import com.quiz.models.MapUserQuestionChoice;

public interface MapUserQuestionChoiceRepository extends JpaRepository<MapUserQuestionChoice, Integer> {

	List<MapUserQuestionChoice> findAllByFkPaperAndFkUser(Integer paperId, Integer userId);
}