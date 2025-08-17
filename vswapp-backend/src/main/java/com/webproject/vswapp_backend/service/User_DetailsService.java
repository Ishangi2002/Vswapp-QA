package com.webproject.vswapp_backend.service;

import com.webproject.vswapp_backend.dto.User_DetailsDto;
import java.util.List;

public interface User_DetailsService {
    User_DetailsDto createUser_Details(User_DetailsDto userDetailsDto);
    User_DetailsDto getUserDetailsById(Long id);
    List<User_DetailsDto> getAllUserDetails();
    User_DetailsDto updateUserDetails(Long id, User_DetailsDto updatedDetails);
    void deleteUserDetails(Long id);
}
