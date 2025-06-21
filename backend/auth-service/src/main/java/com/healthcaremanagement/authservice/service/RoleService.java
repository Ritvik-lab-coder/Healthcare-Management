package com.healthcaremanagement.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcaremanagement.authservice.model.Role;
import com.healthcaremanagement.authservice.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String roleName) {

        Role role = roleRepository.findByName(roleName);

        return role;
    }

    public List<Role> findRoles() {

        List<Role> roles = roleRepository.findAll();

        return roles;
    }
}
