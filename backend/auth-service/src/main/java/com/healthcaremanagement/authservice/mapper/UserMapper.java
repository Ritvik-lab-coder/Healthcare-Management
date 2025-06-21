package com.healthcaremanagement.authservice.mapper;

import java.time.LocalDate;

import com.healthcaremanagement.authservice.dto.UserRequestDTO;
import com.healthcaremanagement.authservice.dto.UserResponseDTO;
import com.healthcaremanagement.authservice.enums.Gender;
import com.healthcaremanagement.authservice.model.User;

public class UserMapper {

    public static User toModel(UserRequestDTO userRequestDTO) {

        User user = new User();

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setContact(userRequestDTO.getContact());

        Gender gender = Gender.valueOf(userRequestDTO.getGender().toUpperCase());
        user.setGender(gender);

        user.setDateOfBirth(LocalDate.parse(userRequestDTO.getDateOfBirth()));
        user.setAddress(userRequestDTO.getAddress());
        user.setCreatedAt(LocalDate.now());

        return user;
    }

    public static UserResponseDTO toDTO(User user) {

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId().toString());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setGender(user.getGender().toString());
        userResponseDTO.setDateOfBirth(user.getDateOfBirth().toString());
        userResponseDTO.setContact(user.getContact());
        userResponseDTO.setAddress(user.getAddress());
        userResponseDTO.setRole(user.getRole().getName());

        return userResponseDTO;
    }
}
