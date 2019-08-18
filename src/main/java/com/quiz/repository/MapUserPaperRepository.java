package com.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.quiz.models.MapUserPaper;

public interface MapUserPaperRepository extends JpaRepository<MapUserPaper, Integer> {

	MapUserPaper findAllByFkPaperAndFkUser(Integer fkPaperId, Integer fkUserId);

	Boolean existsByFkUserAndFkPaper(Integer userid, Integer paperId);
	
	@Modifying
	@Query("update MapUserPaper u set u.marks = ?1 where u.fkUser = ?2 and u.fkPaper=?3")
	void updateMarks(Integer marks, Integer userId,Integer paperId);

	Boolean existsByFkUserAndFkPaperAndMarksIsNull(Integer userid, Integer paperId);
}