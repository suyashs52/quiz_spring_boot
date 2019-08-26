package com.quiz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quiz.models.MapUserPaper;
import com.quiz.models.Paper;
import com.quiz.repository.MapUserPaperRepository;
import com.quiz.repository.PaperRepository;
import com.quiz.security.UserPrinciple;
import com.quiz.utils.ResourceNotFoundException;

@Service
public class PaperServiceImpl implements PaperService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaperServiceImpl.class);

	@Autowired
	PaperRepository paperRepository;

	@Autowired
	MapUserPaperRepository mapUserPaperRepository;

	@Override
	public Map<String, Object> findAll(Pageable pageable, UserPrinciple user) {
		List<MapUserPaper> mup = mapUserPaperRepository.findAllByFkUser(user.getId());
		Page<Paper> pagableItems;
		if ("ADMIN".equals(user.getRole())) {

			pagableItems = paperRepository.findAll(pageable);
		} else {
			List<Integer> fkPaper = mup.stream().map(m -> m.getFkPaper()).collect(Collectors.toList());
			pagableItems = paperRepository.findAllByIdIn(pageable, fkPaper);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("page", pagableItems.getTotalPages());
		map.put("count", pagableItems.getTotalElements());
		map.put("content", pagableItems.getContent());
		map.put("pageSize", pagableItems.getSize());
		map.put("mup", mup);

		return map;
	}

	@Override
	public Optional<Paper> findById(Integer id) {

		return paperRepository.findById(id);
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
