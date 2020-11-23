package it.lokole.jobseek.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AnnouncementDto {

	private Long id;
	private String title;
	private String content;
	private Instant createdOn;
	private Instant updatedOn;
	private String username;
}
