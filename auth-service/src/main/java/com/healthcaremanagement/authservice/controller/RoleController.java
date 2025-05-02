package com.healthcaremanagement.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcaremanagement.authservice.model.Role;
import com.healthcaremanagement.authservice.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/{name}")
    public ResponseEntity<Role> findByName(@PathVariable String name) {

        Role role = roleService.findByName(name);

        return ResponseEntity.ok().body(role);
    }

    @GetMapping
    public ResponseEntity<List<Role>> findRoles() {

        List<Role> roles = roleService.findRoles();
        
        return ResponseEntity.ok().body(roles);
    }
}
