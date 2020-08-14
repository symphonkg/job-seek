package it.lokole.jobseek.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

	private String name;
	private String surname;
	private String username;
	private String password;
	private String email;
	
	
}
