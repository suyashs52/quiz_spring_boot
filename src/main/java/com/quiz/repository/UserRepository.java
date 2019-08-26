package com.quiz.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.quiz.models.User;
import com.quiz.projection.UserProjection;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByPhone(String phone);

	public Page<UserProjection> findAllProjectedBy(Pageable pageable);

	@Modifying
	@Query("update User u set u.password = ?2 where u.id = ?1")
	void setPasswordUser(Integer id, String password);
}
