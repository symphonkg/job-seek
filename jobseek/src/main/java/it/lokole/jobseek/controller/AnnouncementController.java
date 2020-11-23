package it.lokole.jobseek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lokole.jobseek.dto.AnnouncementDto;
import it.lokole.jobseek.service.AnnouncementService;

@RestController
@RequestMapping("/api/jobseek/announcements")
public class AnnouncementController {

	@Autowired
	AnnouncementService announcementService; 
	
	@PostMapping
	public ResponseEntity createAnnouncement(@RequestBody AnnouncementDto announcementDto) {
		announcementService.createAnnouncement(announcementDto);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<AnnouncementDto>> showAllAnnouncements() {
		return new ResponseEntity<List<AnnouncementDto>>(announcementService.showAllAnnouncements(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AnnouncementDto> getSingleAnnouncement(@PathVariable @RequestBody Long id) {
		return new ResponseEntity<AnnouncementDto>(announcementService.readSingleAnnouncement(id), HttpStatus.OK);
	}

}
