package com.healthcaremanagement.authservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.healthcaremanagement.authservice.dto.LoginRequestDTO;
import com.healthcaremanagement.authservice.dto.LoginResponseDTO;
import com.healthcaremanagement.authservice.dto.UpdateUserProfileRequestDTO;
import com.healthcaremanagement.authservice.dto.UserRequestDTO;
import com.healthcaremanagement.authservice.dto.UserResponseDTO;
import com.healthcaremanagement.authservice.enums.Gender;
import com.healthcaremanagement.authservice.exception.EmailAlreadyInUseException;
import com.healthcaremanagement.authservice.exception.InternalServerErrorException;
import com.healthcaremanagement.authservice.exception.InvalidCredentialsException;
import com.healthcaremanagement.authservice.exception.UserNotFoundException;
import com.healthcaremanagement.authservice.mapper.UserMapper;
import com.healthcaremanagement.authservice.model.Role;
import com.healthcaremanagement.authservice.model.User;
import com.healthcaremanagement.authservice.repository.RoleRepository;
import com.healthcaremanagement.authservice.repository.UserRepository;
import com.healthcaremanagement.authservice.security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    @Qualifier("patientWebClient")
    private WebClient patientWebClient;

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {

        Optional<User> existingUser = userRepository.findByEmail(userRequestDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyInUseException("Email is already in use");
        }

        try {

            User user = UserMapper.toModel(userRequestDTO);

            Role userRole = roleRepository.findByName(userRequestDTO.getRole());
            user.setRole(userRole);

            String hashedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
            user.setPassword(hashedPassword);

            userRepository.save(user);

            return UserMapper.toDTO(user);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {

        try {

            String email = loginRequestDTO.getEmail();
            String password = loginRequestDTO.getPassword();

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new InvalidCredentialsException("Invalid email of password");
            }

            UserResponseDTO userResponseDTO = UserMapper.toDTO(user);
            String token = jwtUtil.generateToken(email, user.getId());

            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setUserResponseDTO(userResponseDTO);
            loginResponseDTO.setToken(token);

            return loginResponseDTO;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public List<UserResponseDTO> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();

        for (User user : users) {

            UserResponseDTO userResponseDTO = UserMapper.toDTO(user);
            userResponseDTOs.add(userResponseDTO);
        }

        return userResponseDTOs;
    }

    public UserResponseDTO getUserById(UUID id) {

        try {

            Optional<User> optionalUser = userRepository.findById(id);

            if (optionalUser.isPresent()) {

                User user = optionalUser.get();

                UserResponseDTO userResponseDTO = UserMapper.toDTO(user);

                return userResponseDTO;
            } else {
                throw new UserNotFoundException("User not found");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public UserResponseDTO getUserProfile() {

        try {

            String authHeader = request.getHeader("Authorization");
            String token = authHeader.substring(7);
            String email = jwtUtil.extractEmail(token);

            Optional<User> optionalUser = userRepository.findByEmail(email);

            if (optionalUser.isPresent()) {

                User user = optionalUser.get();
                UserResponseDTO userResponseDTO = UserMapper.toDTO(user);

                return userResponseDTO;
            } else {
                throw new UserNotFoundException("User not found");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public UserResponseDTO updateProfile(UUID id, UpdateUserProfileRequestDTO updateUserProfileRequestDTO) {

        try {

            Optional<User> optionalUser = userRepository.findById(id);

            if (optionalUser.isPresent()) {

                User user = optionalUser.get();

                if (updateUserProfileRequestDTO.getName() != null)
                    user.setName(updateUserProfileRequestDTO.getName());

                if (updateUserProfileRequestDTO.getEmail() != null)
                    user.setEmail(updateUserProfileRequestDTO.getEmail());

                if (updateUserProfileRequestDTO.getPassword() != null) {

                    String hashedPassword = passwordEncoder.encode(updateUserProfileRequestDTO.getPassword());
                    user.setPassword(hashedPassword);
                }

                if (updateUserProfileRequestDTO.getDateOfBirth() != null)
                    user.setDateOfBirth(LocalDate.parse(updateUserProfileRequestDTO.getDateOfBirth()));

                if (updateUserProfileRequestDTO.getAddress() != null)
                    user.setAddress(updateUserProfileRequestDTO.getAddress());

                if (updateUserProfileRequestDTO.getContact() != null)
                    user.setContact(updateUserProfileRequestDTO.getContact());

                if (updateUserProfileRequestDTO.getGender() != null) {

                    Gender gender = Gender.valueOf(updateUserProfileRequestDTO.getGender().toUpperCase());
                    user.setGender(gender);
                }

                userRepository.save(user);

                UserResponseDTO userResponseDTO = UserMapper.toDTO(user);

                return userResponseDTO;
            } else {
                throw new UserNotFoundException("User not found");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public void deleteUser(UUID id) {

        try {
            
            Optional<User> optionalUser = userRepository.findById(id);

            if (optionalUser.isPresent()) {

                User user = optionalUser.get();

                if (user.getRole().getName().equals("ROLE_PATIENT")) {

                    String authHeader = request.getHeader("Authorization");
                    String uuid = user.getId().toString();

                    patientWebClient.delete()
                        .uri("/patient/delete/" + uuid)
                        .header("Authorization", authHeader)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                    userRepository.deleteById(id);
                } else {
                    userRepository.deleteById(id);
                }
            } else {
                throw new UserNotFoundException("User not found");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }
}
