package it.lokole.jobseek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.lokole.jobseek.dto.RegistrationRequest;
import it.lokole.jobseek.model.User;
import it.lokole.jobseek.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;
	
	public void register(RegistrationRequest registrationRequest) {
		User user = new User();
		user.setName(registrationRequest.getName());
		user.setSurname(registrationRequest.getSurname());
		user.setUsername(registrationRequest.getUsername());
		user.setEmail(registrationRequest.getEmail());
		userRepository.save(user);
	}

}
