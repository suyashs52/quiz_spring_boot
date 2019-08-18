package com.quiz.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quiz.models.Login;
import com.quiz.models.Paper;
import com.quiz.models.User;
import com.quiz.response.JwtResponse;
import com.quiz.security.UserPrinciple;
import com.quiz.security.jwt.JwtProvider;
import com.quiz.service.PaperService;
import com.quiz.service.UserDetailService;
import com.quiz.utils.ErrorDetails;
import com.quiz.utils.ResourceNotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600 ,allowedHeaders="*")

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/result/")
public class ResultRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ResultRestController.class);
	 
	@Autowired
	UserDetailService userService;
	
	@Autowired
	PaperService paperService;

 
 

	@PostMapping("/logins")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> authenticateUser() {
		//ResponseEntity<Object> obj = 
		 
		 
		return ResponseEntity.ok(11);
	}
	@PostMapping("/user")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUser() {
		//ResponseEntity<Object> obj = 
		 
		 
		return ResponseEntity.ok(11);
	}
	@GetMapping("/users/{pageno}") // all list by pagination
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllUser(@PathVariable(value = "pageno") Integer pageno) {

		Pageable pageable = PageRequest.of(pageno, 10);
		Map<String, Object> map = userService.findAll(pageable);

		return ResponseEntity.ok(map);
	}
	
	  
	
	@GetMapping("/paper/{pageno}") // all list by pagination
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllPaper(@PathVariable(value = "pageno") Integer pageno) {

		Pageable pageable = PageRequest.of(pageno, 10);
		Map<String, Object> map = paperService.findAll(pageable);

		return ResponseEntity.ok(map);
	}
	
	
	
	@PostMapping("/createpaper")
	public ResponseEntity<Object> createPaper(@Valid @RequestBody Paper paper, BindingResult result)
			throws ResourceNotFoundException {
		ResponseEntity<Object> obj = getError(result);
		if (obj != null) {
			return obj;
		}
 
		paperService.savePaper(paper);

		return ResponseEntity.ok().body("Paper created successfully!");
	}

	@PostMapping("/updatepaper")
	public ResponseEntity<Object> updatePaper(@Valid @RequestBody Paper paper, BindingResult result)
			throws ResourceNotFoundException {
		ResponseEntity<Object> obj = getError(result);
		if (obj != null) {
			return obj;
		}

	 
		paperService.updatePaper(paper); 

		return ResponseEntity.ok().body("Paper data updated successfully!");
	}
	
	
	
	
	ResponseEntity<Object> getError(BindingResult result) {
		if (result.getErrorCount() > 0) {

			List<String> errors = new ArrayList<String>();
			for (FieldError error : result.getFieldErrors()) {
				errors.add(error.getField() + ": " + error.getDefaultMessage());
			}
			for (ObjectError error : result.getGlobalErrors()) {
				errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
			}
			ErrorDetails errorDetails = new ErrorDetails(new Date(), "error", "validation error",
					HttpStatus.BAD_REQUEST, errors);
			return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
		} else {
			return null;
		}
	}

}
