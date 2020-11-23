package it.lokole.jobseek.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Announcement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long announcementid;
	
	@NotBlank
	@Column
	private String title;
	
	@NotEmpty
	@Lob
	@Column
	private String content;
	
	@Column
	private Instant createdOn;
	
	@Column
	private Instant updatedOn;
	
	@ManyToOne
	@JoinColumn(name = "userid", nullable = false)
	private User user;
	
	@Column(name="owner")
	private String username;
	
	
}
