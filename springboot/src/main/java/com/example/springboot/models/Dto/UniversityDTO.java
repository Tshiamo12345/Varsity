package com.example.springboot.models.Dto;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDTO {
 
    private String universityName;

    private String gmail;

    private String tellphoneNo;

    private String culture;

    private String description;

    private MultipartFile logo;

    private String closingDate;
    
    private String openingDate;
 

}
