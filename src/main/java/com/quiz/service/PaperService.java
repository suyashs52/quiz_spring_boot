package com.quiz.service;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.quiz.models.Paper;
import com.quiz.utils.ResourceNotFoundException;

public interface PaperService {
	Map<String, Object> findAll(Pageable pageable);

	Optional<Paper> findById(Integer itemId);

	Paper savePaper(@Valid Paper paper) throws ResourceNotFoundException;

	void updatePaper(@Valid Paper paper) throws ResourceNotFoundException;

	void delete(Paper paper);

}
