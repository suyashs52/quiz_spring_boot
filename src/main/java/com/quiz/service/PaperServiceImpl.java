package com.quiz.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quiz.models.Paper;
import com.quiz.repository.PaperRepository;
import com.quiz.utils.ResourceNotFoundException;

@Service
public class PaperServiceImpl implements PaperService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaperServiceImpl.class);

	@Autowired
	PaperRepository paperRepository;

	@Override
	public Map<String, Object> findAll(Pageable pageable) {
		Page<Paper> pagableItems = paperRepository.findAll(pageable);

		Map<String, Object> map = new HashMap<>();
		map.put("page", pagableItems.getTotalPages());
		map.put("count", pagableItems.getTotalElements());
		map.put("content", pagableItems.getContent());
		map.put("pageSize", pagableItems.getSize());

		return map;
	}

	@Override
	public Optional<Paper> findById(Integer id) {
		 
		return paperRepository.findById(Long.valueOf(id));
	}

	@Override
	public void delete(Paper paper) {
		paperRepository.delete(paper);
	}

	@Override
	public Paper savePaper(@Valid Paper paper) throws ResourceNotFoundException {
		if (paperRepository.existsByName(paper.getName())) {
			LOGGER.info("Paper Name is already taken!");
			throw new ResourceNotFoundException("Paper with name exist!");

		}

		return paperRepository.save(paper);
	}

	@Override
	@Transactional
	public void updatePaper(@Valid Paper paper) {

		paperRepository.updatePaper(paper.getId(), paper.getTotalTime(), paper.getDesc());

	}

}
