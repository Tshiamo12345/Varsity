package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springboot.models.University;

@Repository
public interface UniversityRepo extends JpaRepository<University, String> {

    @Query(value = "SELECT * FROM university ORDER BY university_id ASC LIMIT 1", nativeQuery = true)
    University findFirstUniversity();
}
