package com.example.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.springboot.models.Role;
import com.example.springboot.service.RoleService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final Logger logger = LoggerFactory.getLogger(RoleController.class);
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {

        try {
            logger.info(role.getRoleName());
            Role roledb = roleService.createRole(role);
            return new ResponseEntity<>(roledb, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.error("Failing to create a Role ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // deleting a role
    @Transactional
    @DeleteMapping
    public ResponseEntity<Role> deleteRole(@RequestParam("roleId") String roleId) {

        try {

            logger.info("Preparing to delete a role");
            roleService.deleteRole(roleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (RuntimeException e) {

            logger.error("Failing tod delete a role ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @Transactional
    @GetMapping 
    public ResponseEntity<List<Role>> getAllRoles() {
        try {
            List<Role> roles = roleService.getRoles();
            return new ResponseEntity<>(roles, HttpStatus.FOUND);
        } catch (RuntimeException e) {
            logger.error("The Server failed to fetch ", e);
            return new ResponseEntity<List<Role>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @Transactional
    @PutMapping
    public ResponseEntity<Role> updateRole(@RequestBody Role role){

        try{
            Role roleUpdated = roleService.updateRole(role);
            return new ResponseEntity<>(roleUpdated,HttpStatus.OK);
        }catch(RuntimeException e){
            logger.error("The System Failed to Update a role", e);
            return new ResponseEntity<Role>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
