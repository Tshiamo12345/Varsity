package com.example.springboot.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "university")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "university_id", updatable = false, nullable = false)
    private String universityId;

    @Column(name = "logo", columnDefinition = "longblob")
    private byte[] logo;

    @Column(name = "name", nullable = false)
    private String universityName;

    @Column(name="gmail")
    private String gmail;
    
    @Pattern(regexp = "^[0-9]{10}$")
    @Column(name = "tellphone_No")
    private String tellphoneNo;

    @Column(name = "culture")
    private String culture;
    
    @Column(name="description")
    private String description;

    @Column(name = "opening_date", nullable = false)
    private LocalDate openingDate;

    @Column(name = "closing_date", nullable = false)
    private LocalDate closingDate;



}
