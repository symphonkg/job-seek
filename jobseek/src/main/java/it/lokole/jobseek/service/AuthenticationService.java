package it.lokole.jobseek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.lokole.jobseek.dto.RegistrationRequest;
import it.lokole.jobseek.model.User;
import it.lokole.jobseek.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void register(RegistrationRequest registrationRequest) {
		User user = new User();
		user.setName(registrationRequest.getName());
		user.setSurname(registrationRequest.getSurname());
		user.setUsername(registrationRequest.getUsername());
		user.setEmail(registrationRequest.getEmail());
		user.setPassword(passwordEncode(registrationRequest.getPassword()));
		userRepository.save(user);
	}

	private String passwordEncode(String password) {
		return passwordEncoder.encode(password);
	}

}
