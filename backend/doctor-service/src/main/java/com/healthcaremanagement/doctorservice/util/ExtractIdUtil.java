package com.healthcaremanagement.doctorservice.util;

import java.util.UUID;

import com.healthcaremanagement.doctorservice.security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

public class ExtractIdUtil {

    public static UUID extractUUID(HttpServletRequest request, JwtUtil jwtUtil) {

        try {
            
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new IllegalArgumentException("Invalid token");
            }

            String token = authHeader.substring(7);
            String id = jwtUtil.extractId(token);
            UUID uuid = UUID.fromString(id);

            return uuid;
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract id");
        }
    }
}
