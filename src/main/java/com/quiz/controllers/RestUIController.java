package com.quiz.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/ui/")
public class RestUIController {
	@GetMapping("test")
	public String getUser(HttpServletRequest request) {

	return "sdf";
	}
}
