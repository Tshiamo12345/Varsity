package com.example.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.models.University;
import com.example.springboot.models.Dto.UniversityDTO;
import com.example.springboot.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/university")
public class UniversityController {

    private static final Logger logger = LoggerFactory.getLogger(UniversityController.class);
    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    // creating university

    @PostMapping
    public ResponseEntity<University> createUniversity(@RequestBody UniversityDTO universityDTO) {

        try {

            logger.info(universityDTO.getClosingDate());
            University uni = universityService.addUniverstity(universityDTO);
            logger.info("The University Has been created ", uni);
            return new ResponseEntity<>(uni, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.error("Error creating university ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<University> getUniversity() {

        try {

            University uni = universityService.getUniversity();
            logger.info("The University details have been returned ");
            return new ResponseEntity<>(uni, HttpStatus.FOUND);
            
        } catch (RuntimeException e) {
            logger.error("System Failed to fetvh for University Details", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
