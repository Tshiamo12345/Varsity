package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.models.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,String> {
 
}
