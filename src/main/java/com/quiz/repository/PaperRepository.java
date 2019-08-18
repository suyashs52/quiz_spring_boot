package com.quiz.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.quiz.models.Paper;
import com.quiz.models.User;
import com.quiz.projection.UserProjection;

public interface PaperRepository extends PagingAndSortingRepository<Paper, Integer> {
	Optional<Paper> findById(Long id);

	Boolean existsById(int id);
 
	Boolean existsByName(String name);

	public Page<Paper> findAll(Pageable pageable);

	@Modifying
	@Query("update Paper u set u.totalTime = ?2, u.desc=?3 where u.id = ?1")
	void updatePaper(long id, Integer totalTime,String desc);
}
