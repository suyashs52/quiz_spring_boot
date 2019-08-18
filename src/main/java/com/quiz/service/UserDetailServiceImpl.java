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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quiz.models.User;
import com.quiz.projection.UserProjection;
import com.quiz.repository.UserRepository;
import com.quiz.security.UserPrinciple;
import com.quiz.utils.ResourceNotFoundException;

@Service
public class UserDetailServiceImpl implements UserDetailsService, UserDetailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public Map<String, Object> findAll(Pageable pageable) {
		Page<UserProjection> pagableItems = userRepository.findAllProjectedBy(pageable);

		Map<String, Object> map = new HashMap<>();
		map.put("page", pagableItems.getTotalPages());
		map.put("count", pagableItems.getTotalElements());
		map.put("content", pagableItems.getContent());
		map.put("pageSize", pagableItems.getSize());

		return map;
	}

	@Override
	public Optional<User> findById(Integer user) {
		return userRepository.findById(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public User saveUser(@Valid User user) throws ResourceNotFoundException {
		if (userRepository.existsByUsername(user.getUsername())) {
			LOGGER.info("Username is already taken!");
			throw new ResourceNotFoundException("Username is already taken!");

		}

		if (userRepository.existsByPhone(user.getPhone())) {
			throw new ResourceNotFoundException("User Phone is already taken!");

		}

		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void updateUser(@Valid User user) {
		//userRepository.save(user);
		 userRepository.setPasswordUser(user.getId(), user.getPassword());

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
		return UserPrinciple.build(user);
	}

}
