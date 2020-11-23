package it.lokole.jobseek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lokole.jobseek.dto.LoginRequest;
import it.lokole.jobseek.dto.RegistrationRequest;
import it.lokole.jobseek.service.AuthenticationService;

@RestController
@RequestMapping("/api/jobseek")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody RegistrationRequest registrationRequest) {
		authenticationService.register(registrationRequest); 
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest){
		return authenticationService.login(loginRequest);
	}
}
