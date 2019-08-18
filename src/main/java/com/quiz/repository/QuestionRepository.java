package com.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.quiz.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	List<Question> findAllByFkPaperId(Integer fkPaperId);
	@Modifying
	@Query("update Question u set u.fkCorrectChoice = ?1 where u.id = ?2")
	void updateFkCorrectChoiceById(Integer fkCorrectChoice, Integer id);
}