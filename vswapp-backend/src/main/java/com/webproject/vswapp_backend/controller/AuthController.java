package com.webproject.vswapp_backend.controller;

import com.webproject.vswapp_backend.dto.UserDto;
import com.webproject.vswapp_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") // Allow React frontend
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto loginRequest) {
        Map<String, Object> response = new HashMap<>();

        // Find user by email
        UserDto user = userService.findByEmail(loginRequest.getEmail());

        if (user == null) {
            response.put("success", false);
            response.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // Check password
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            response.put("success", false);
            response.put("message", "Invalid password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // Login success
        response.put("success", true);
        response.put("message", "Login successful");
        response.put("token", "dummy-token-123"); // Replace with real JWT later
        response.put("userId", user.getId());
        return ResponseEntity.ok(response);
    }
}
