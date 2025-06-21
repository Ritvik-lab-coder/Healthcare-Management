package com.healthcaremanagement.authservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcaremanagement.authservice.dto.LoginRequestDTO;
import com.healthcaremanagement.authservice.dto.LoginResponseDTO;
import com.healthcaremanagement.authservice.dto.UpdateUserProfileRequestDTO;
import com.healthcaremanagement.authservice.dto.UserRequestDTO;
import com.healthcaremanagement.authservice.dto.UserResponseDTO;
import com.healthcaremanagement.authservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO userResponseDTO = userService.registerUser(userRequestDTO);

        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {

        LoginResponseDTO loginResponseDTO = userService.loginUser(loginRequestDTO);

        return new ResponseEntity<>(loginResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        List<UserResponseDTO> userResponseDTOs = userService.getAllUsers();

        return ResponseEntity.ok().body(userResponseDTOs);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDTO> getProfile() {

        UserResponseDTO userResponseDTO = userService.getUserProfile();

        return ResponseEntity.ok().body(userResponseDTO);
    }

    @PutMapping("/profile/update/{id}")
    public ResponseEntity<UserResponseDTO> updateProfile(@PathVariable("id") UUID id, @RequestBody UpdateUserProfileRequestDTO updateUserProfileRequestDTO) {

        UserResponseDTO userResponseDTO = userService.updateProfile(id, updateUserProfileRequestDTO);

        return ResponseEntity.ok().body(userResponseDTO); 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable("id") UUID id) {

        userService.deleteUser(id);

        return ResponseEntity.ok().body("User deleted successfully");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") UUID id) {

        UserResponseDTO userResponseDTO = userService.getUserById(id);

        return ResponseEntity.ok().body(userResponseDTO);
    }
}
