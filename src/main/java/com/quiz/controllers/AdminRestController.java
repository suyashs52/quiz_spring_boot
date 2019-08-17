package com.quiz.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.service.UserDetailService;

@RequestMapping("/api/admin/")
@RestController
public class AdminRestController {
	
	@Autowired
	UserDetailService userService;

	@GetMapping("/users") // all list by pagination
	//@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<?> itemsPageable(@RequestParam("pageNo") Integer pageNo) {

		Pageable pageable = PageRequest.of(pageNo, 10);
		Map<String, Object> map = userService.findAll(pageable);

		return ResponseEntity.ok(map);
	}
}
