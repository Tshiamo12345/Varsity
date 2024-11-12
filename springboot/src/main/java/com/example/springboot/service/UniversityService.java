package com.example.springboot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.example.springboot.Exception.UnsuccessfulException;
import com.example.springboot.models.University;
import com.example.springboot.models.Dto.UniversityDTO;
import com.example.springboot.repository.UniversityRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;

@Service
public class UniversityService {

    private static final Logger logger = LoggerFactory.getLogger(UniversityService.class);
    private final UniversityRepo universityRepo;

    public UniversityService(UniversityRepo universityRepo) {
        this.universityRepo = universityRepo;
    }

    @Transactional
    public University addUniverstity(UniversityDTO universityDTO) {
        University university;

        try {

            byte[] logo ;

            String universityName = universityDTO.getUniversityName();
            String gmail = universityDTO.getGmail();
            String culture = universityDTO.getCulture();
            String description = universityDTO.getDescription();

            MultipartFile universityLogo = universityDTO.getLogo();
            if (universityLogo != null) {
                logo = universityLogo.getBytes();
            }
            
            LocalDate closingDate = makeDate(universityDTO.getClosingDate());
            LocalDate openingDate = makeDate(universityDTO.getOpeningDate());
            university = new University();
            university.setCulture(culture);
            university.setDescription(description);
            university.setUniversityName(universityName);
            university.setGmail(gmail);
            university.setClosingDate(closingDate);
            university.setOpeningDate(openingDate);
            university.setTellphoneNo(universityDTO.getTellphoneNo());
            universityRepo.save(university);
            return university;

        } catch (Exception e) {
            logger.error("Failed tp create university ", e);
            throw new UnsuccessfulException("Failed to create University");
        }
    }

    public University getUniversity(){
        try{
            
            return universityRepo.findFirstUniversity();

        }catch(Exception e){
            logger.error("Failing to get University details ", e);
            throw new UnsuccessfulException("Failed to get Unversity Details ");
        }        
    }
    //making a date from a string 
    private LocalDate makeDate(String dateString) {

        String dateDetails[] = dateString.split("-");

        int year = Integer.parseInt(dateDetails[0]);
        int month = Integer.parseInt(dateDetails[1]);
        int dayOfMonth = Integer.parseInt(dateDetails[2]);
        LocalDate date = LocalDate.of(year, month, dayOfMonth);
        return date;
    
    
    }



    
}
