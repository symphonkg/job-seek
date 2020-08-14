package it.lokole.jobseek.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor 
@Getter
@Setter
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
	@NotBlank
	private String username;
	
	@Column
	@NotBlank
	private String password;
	
	@Column(unique=true)
	@Email
	@NotBlank
	private String email;
	
	@Column
	@OneToMany(mappedBy = "user")
	private Set<Announcement> annoucements; 
	
	

	
}
