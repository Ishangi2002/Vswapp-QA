package com.webproject.vswapp_backend.controller;

import com.webproject.vswapp_backend.dto.User_DetailsDto;
import com.webproject.vswapp_backend.service.User_DetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/user-details")
@CrossOrigin(origins = "http://localhost:5173")
public class User_DetailsController {

    private User_DetailsService userDetailsService;

    // Create new User_Details

    @PostMapping
    public ResponseEntity<User_DetailsDto> createUserDetails(@RequestBody User_DetailsDto userDetailsDto) {
        User_DetailsDto savedDetails = userDetailsService.createUser_Details(userDetailsDto);
        return new ResponseEntity<>(savedDetails, HttpStatus.CREATED);
    }

    // Get User_Details by ID
    @GetMapping("{id}")
    public ResponseEntity<User_DetailsDto> getUserDetailsById(@PathVariable("id") Long id) {
        User_DetailsDto detailsDto = userDetailsService.getUserDetailsById(id);
        return ResponseEntity.ok(detailsDto);
    }

    // Get all User_Details
    @GetMapping
    public ResponseEntity<List<User_DetailsDto>> getAllUserDetails() {
        List<User_DetailsDto> allDetails = userDetailsService.getAllUserDetails();
        return ResponseEntity.ok(allDetails);
    }

    // Update User_Details by ID
    @PutMapping("{id}")
    public ResponseEntity<User_DetailsDto> updateUserDetails(@PathVariable("id") Long id,
                                                             @RequestBody User_DetailsDto updatedDetails) {
        User_DetailsDto updated = userDetailsService.updateUserDetails(id, updatedDetails);
        return ResponseEntity.ok(updated);
    }

    // Delete User_Details by ID
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserDetails(@PathVariable Long id) {
        userDetailsService.deleteUserDetails(id);
        return ResponseEntity.ok("User details deleted successfully.");
    }
}
