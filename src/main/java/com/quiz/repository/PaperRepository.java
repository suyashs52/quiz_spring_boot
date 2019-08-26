package com.quiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.quiz.models.Paper;

public interface PaperRepository extends PagingAndSortingRepository<Paper, Integer> {
	Optional<Paper> findById(Integer id);
	

	boolean existsById(Integer id);
 
	Boolean existsByName(String name);

	public Page<Paper> findAll(Pageable pageable);
	public Page<Paper> findAllByIdIn(Pageable pageable,List<Integer> id);
	

	@Modifying
	@Query("update Paper u set u.totalTime = ?2, u.desc=?3 where u.id = ?1")
	void updatePaper(Integer id, Integer totalTime,String desc);
}
