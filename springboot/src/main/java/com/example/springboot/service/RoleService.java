package com.example.springboot.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;

import com.example.springboot.Exception.ObjectNotFoundException;
import com.example.springboot.Exception.UnsuccessfulException;
import com.example.springboot.models.Role;
import com.example.springboot.repository.RoleRepo;
import jakarta.transaction.Transactional;
import java.util.*;

@Service
public class RoleService {

    private final Logger logger = LoggerFactory.getLogger(RoleService.class);
    private final RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;

    }

    // creating a role
    @Transactional
    public Role createRole(Role role) {

        try {

            // checking conditions of a role
            if (role == null || role.getRoleName().isEmpty()) {
                logger.error("Role name cannot be empty");
                throw new UnsuccessfulException("Role name cannot be empty or role cannot be null");
            }

            // saving a role
            Role roledb = roleRepo.save(role);
            return roledb;
        } catch (Exception e) {
            logger.error("Failed create a role ", e);
            throw new UnsuccessfulException("Failed to create a role");
        }

    }

    public void deleteRole(String roleId) {
        java.util.Optional<Role> role;
        try {

            if (roleId.isEmpty() || roleId == null) {
                logger.error("The role name cannot be empty or null");
                throw new UnsuccessfulException("The rolen Name cannot be null ");

            }
            role = roleRepo.findById(roleId);
            if (!role.isPresent()) {
                logger.error("The Role with doesnt exist with ID{} " + roleId);
                throw new UnsuccessfulException("The Role doesn`t exdist " + roleId);
            }

            roleRepo.delete(role.get());
        } catch (Exception e) {
            logger.error("Failing to delete role ", e);
            throw new UnsuccessfulException("Failing to Delete a role ");
        }
    }

    public List<Role> getRoles() {

        try {

            List<Role> roles = roleRepo.findAll();
            return roles;
        } catch (Exception e) {
            logger.error("Failed to fetch data ", e);
            throw new UnsuccessfulException("Failed to get List of roles");
        }
    }

    public Role updateRole(Role role) {
        try {
            Optional<Role> optRole = roleRepo.findById(role.getRoleId());
            if (!optRole.isPresent() || optRole.isEmpty()) {
                logger.error("The Role is not found or is undefined ");
                throw new ObjectNotFoundException("The Role is not found with ID {} " + role.getRoleId());
            }

            return roleRepo.save(role);
        } catch (Exception e) {
            logger.error("Failed to update the Role ", e);
            throw new UnsuccessfulException("Failed to update Role");
        }
    }
}
