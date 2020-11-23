package it.lokole.jobseek.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.lokole.jobseek.dto.AnnouncementDto;
import it.lokole.jobseek.exception.AnnouncementNotFoundException;
import it.lokole.jobseek.model.Announcement;
import it.lokole.jobseek.model.User;
import it.lokole.jobseek.repository.AnnouncementRepository;
import it.lokole.jobseek.repository.UserRepository;

@Service
public class AnnouncementService {
	
	private static final String FOR_ID = "For id ";

	private static final String NESSUN_UTENTE_LOGGATO = "Nessun utente loggato";

	@Autowired
	AuthenticationService authService;
	
	@Autowired
	AnnouncementRepository announcementRepository;
	
	@Autowired
	UserRepository userRepository;

	public void createAnnouncement(AnnouncementDto announcementDto) {
		announcementRepository.save(mapFromDtoToAnnouncement(announcementDto));
	}

	public List<AnnouncementDto> showAllAnnouncements() {
		List<Announcement> announcements = announcementRepository.findAll();
		return announcements.stream().map(this::mapFromAnnouncementToDto).collect(Collectors.toList());
	}
	
	public AnnouncementDto readSingleAnnouncement(Long id) {
		Announcement announcement = announcementRepository.findById(id).orElseThrow(() -> new AnnouncementNotFoundException(FOR_ID + id));
		return mapFromAnnouncementToDto(announcement);
	}
	
	private AnnouncementDto mapFromAnnouncementToDto(Announcement announcement) {
		AnnouncementDto announcementDto = new AnnouncementDto();
		announcementDto.setId(announcement.getAnnouncementid());
		announcementDto.setTitle(announcement.getTitle());
		announcementDto.setContent(announcement.getContent());
		announcementDto.setCreatedOn(announcement.getCreatedOn());
		announcementDto.setUpdatedOn(announcement.getUpdatedOn());
		announcementDto.setUsername(announcement.getUsername());
		return announcementDto;
	}
	
	private Announcement mapFromDtoToAnnouncement(AnnouncementDto announcementDto) {
		Announcement announcement = new Announcement();
		announcement.setAnnouncementid(announcementDto.getId());
		announcement.setTitle(announcementDto.getTitle());
		announcement.setContent(announcementDto.getContent());
		Instant currentInstant = announcementDto.getCreatedOn();
		announcement.setCreatedOn(currentInstant != null ? currentInstant : Instant.now());
		announcement.setUpdatedOn(announcementDto.getUpdatedOn());
		String username = geCurrentlyLoggedUser();
		announcement.setUsername(username);
		announcement.setUser(getUserByUsername(username));
		return announcement;
	}
	
	/**
	 * get user by username
	 * @param username
	 * @return
	 */
	private User getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(UserDetailsServiceImpl.USERNAME_NOT_FOUND + username));
	}

	/**
	 * recupera l'utente loggato da AuthenticationService
	 * @return
	 */
	private String geCurrentlyLoggedUser() {
		org.springframework.security.core.userdetails.User user = authService.getCurrentUser().orElseThrow(()-> new IllegalArgumentException(NESSUN_UTENTE_LOGGATO));
		return user.getUsername();
	}
}
