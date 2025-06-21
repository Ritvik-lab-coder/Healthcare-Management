package com.healthcaremanagement.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcaremanagement.authservice.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByName(String roleName);
}
