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
import com.quiz.models.MapUserQuestionChoice;
import com.quiz.models.Option;
import com.quiz.models.Paper;
import com.quiz.models.Question;
import com.quiz.models.User;
import com.quiz.response.JwtResponse;
import com.quiz.security.UserPrinciple;
import com.quiz.security.jwt.JwtProvider;
import com.quiz.service.PaperService;
import com.quiz.service.QuestionService;
import com.quiz.service.UserDetailService;
import com.quiz.utils.ErrorDetails;
import com.quiz.utils.ResourceNotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/target/")
public class RestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestController.class);
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailService userService;

	@Autowired
	PaperService paperService;
	@Autowired
	QuestionService questionService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody Login loginRequest, BindingResult result) {
		ResponseEntity<Object> obj = getError(result);
		if (obj != null) {
			return obj;
		}
		loginRequest.setPassword(loginRequest.getCredential());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

		String role = userPrincipal.getAuthorities().toString();
		String name = userPrincipal.getName();
		String jwt = jwtProvider.generateJwtToken(authentication);

		return ResponseEntity.ok(new JwtResponse(jwt, name, role));
	}

	@PostMapping("/logins")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> authenticateUser() {
		// ResponseEntity<Object> obj =

		return ResponseEntity.ok(11);
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUser() {
		// ResponseEntity<Object> obj =

		return ResponseEntity.ok(11);
	}

	@GetMapping("/users/{pageno}") // all list by pagination
	// @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllUser(@PathVariable(value = "pageno") Integer pageno) {

		Pageable pageable = PageRequest.of(pageno, 10);
		Map<String, Object> map = userService.findAll(pageable);

		return ResponseEntity.ok(map);
	}

	@PostMapping("/signup")
	public ResponseEntity<Object> registerUser(@Valid @RequestBody User signup, BindingResult result)
			throws ResourceNotFoundException {
		ResponseEntity<Object> obj = getError(result);
		if (obj != null) {
			return obj;
		}

		signup.setPassword(encoder.encode(signup.getCredential()));

		userService.saveUser(signup);

		return ResponseEntity.ok().body("User registered successfully!");
	}

	@PostMapping("/updateuser")
	public ResponseEntity<Object> updateUser(@Valid @RequestBody User signup, BindingResult result)
			throws ResourceNotFoundException {
		ResponseEntity<Object> obj = getError(result);
		if (obj != null) {
			return obj;
		}

		signup.setPassword(encoder.encode(signup.getCredential()));

		userService.updateUser(signup);

		return ResponseEntity.ok().body("User registered successfully!");
	}

	@GetMapping("/paper/{pageno}") // all list by pagination
	// @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllPaper(@PathVariable(value = "pageno") Integer pageno) {

		Pageable pageable = PageRequest.of(pageno, 10);
		Map<String, Object> map = paperService.findAll(pageable);

		return ResponseEntity.ok(map);
	}

	@GetMapping("/getpaper/{id}") // all list by pagination
	// @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getPaperById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {

		Paper p = paperService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + id));
		;

		return ResponseEntity.ok().body(p);
	}

	@PostMapping("/createpaper")
	public ResponseEntity<Object> createPaper(@Valid @RequestBody Paper paper, BindingResult result)
			throws ResourceNotFoundException {
		ResponseEntity<Object> obj = getError(result);
		if (obj != null) {
			return obj;
		}

		Paper p = paperService.savePaper(paper);

		return ResponseEntity.ok().body(p);
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

	@PostMapping("/savequestionpaper")
	public ResponseEntity<Object> saveQuestionPaper(@Valid @RequestBody List<Question> ques,
			@Valid @RequestBody List<Option> option, @Valid @RequestBody User user, @Valid @RequestBody Paper paper,
			BindingResult result) {
		ResponseEntity<Object> obj = getError(result);
		if (obj != null) {
			return obj;
		}
		questionService.saveQuestionPaper(ques, option, user, paper);
		return ResponseEntity.ok().body("Question data updated successfully!");
	}

	@PostMapping("/getquestionpaper")
	public ResponseEntity<Object> getQuestionOption(@Valid @RequestBody Integer paperId,
			@Valid @RequestBody Integer userId, BindingResult result) throws ResourceNotFoundException {
		ResponseEntity<Object> obj = getError(result);
		if (obj != null) {
			return obj;
		}
		if (questionService.isValidForQuiz(userId, paperId)) {
			throw new ResourceNotFoundException("Not valid for quiz");
		}

		return ResponseEntity.ok().body(questionService.getQuestionOption(paperId, 0));
	}

	@PostMapping("/getquestionpaperresult")
	public ResponseEntity<Object> getQuestionOptionResult(@Valid @RequestBody Integer paperId,
			@Valid @RequestBody Integer userId, BindingResult result) {
		ResponseEntity<Object> obj = getError(result);
		if (obj != null) {
			return obj;
		}

		return ResponseEntity.ok().body(questionService.getQuestionOption(paperId, userId));
	}

	@PostMapping("/savetestresult")
	public ResponseEntity<Object> saveUserQuestions(@Valid @RequestBody List<MapUserQuestionChoice> muqc) {
		questionService.saveUserQuestions(muqc);
		return ResponseEntity.ok().body(muqc);

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
