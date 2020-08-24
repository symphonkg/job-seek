package it.lokole.jobseek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.lokole.jobseek.dto.LoginRequest;
import it.lokole.jobseek.dto.RegistrationRequest;
import it.lokole.jobseek.model.User;
import it.lokole.jobseek.repository.UserRepository;
import it.lokole.jobseek.security.JwtProvider;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtProvider jwtProvider;
	
	/**
	 * first user registration 
	 * @param registrationRequest
	 */
	public void register(RegistrationRequest registrationRequest) {
		User user = new User();
		user.setName(registrationRequest.getName());
		user.setSurname(registrationRequest.getSurname());
		user.setUsername(registrationRequest.getUsername());
		user.setEmail(registrationRequest.getEmail());
		user.setPassword(passwordEncode(registrationRequest.getPassword()));
		userRepository.save(user);
	}

	/**
	 * encode user password on signup
	 * @param password
	 * @return
	 */
	private String passwordEncode(String password) {
		return passwordEncoder.encode(password);
	}

	
	public String login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwtProvider.generateToken(authentication);
	}

}
