package it.lokole.jobseek.service;

import java.util.Optional;

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
	public User register(RegistrationRequest registrationRequest) {
		User user = new User();
		user.setName(registrationRequest.getName());
		user.setSurname(registrationRequest.getSurname());
		user.setUsername(registrationRequest.getUsername());
		user.setEmail(registrationRequest.getEmail());
		user.setPassword(passwordEncode(registrationRequest.getPassword()));
		
		User savedUser = null;
		try {
			savedUser = userRepository.save(user);
		} catch (Exception e) {
			// TODO : scrivere nei log e personalizzare la gestione dell'errore SQL (ex:UserNotSavedException : throw UserNotSavedException("error while saving user")) 
			e.printStackTrace();
		}
		return savedUser;
	}

	/**
	 * encode user password on signup
	 * @param password
	 * @return String
	 */
	private String passwordEncode(String password) {
		return passwordEncoder.encode(password);
	}

	/**
	 * login utente, genera il token della login
	 * @param loginRequest
	 * @return
	 */
	public String login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwtProvider.generateToken(authentication);
	}

	/**
	 * recupera i dati username e password dell'utente attualmente loggato
	 * @return
	 */
	public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return Optional.of(principal);
		
	}

}
