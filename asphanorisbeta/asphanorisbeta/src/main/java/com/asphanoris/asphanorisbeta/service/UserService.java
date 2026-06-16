package com.asphanoris.asphanorisbeta.service;

import com.asphanoris.asphanorisbeta.domain.User;
import com.asphanoris.asphanorisbeta.dto.UserRequestDTO;
import com.asphanoris.asphanorisbeta.dto.UserResponseDTO;
import com.asphanoris.asphanorisbeta.factory.*;
import com.asphanoris.asphanorisbeta.repository.IUserRepository;
import com.asphanoris.asphanorisbeta.security.PasswordHasherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    @Autowired
    private IUserRepository userRepo;
    
    @Autowired
    private PasswordHasherService pwdHasher;
    
    private UserFactory getUserFactory(UserRequestDTO userDto) {
        switch (userDto.getRole()) {
            case ADMIN:
                return new AdminFactory();
            case DRIVER:
                return new DriverFactory();
            case PASSENGER:
                return new PassengerFactory();
            default:
                throw new IllegalArgumentException("Invalid role");
        }
    }
    
    private UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setSecondName(user.getSecondName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setPhotoUrl(user.getPhotoUrl());
        dto.setRole(user.getRole());
        return dto;
    }
    
    public UserResponseDTO addUser(UserRequestDTO userDto) {
        UserFactory factory = getUserFactory(userDto);
        User user = factory.createUser(userDto);
        user.setPasswordHash(pwdHasher.hash(userDto.getPassword()));
        User saved = userRepo.addUser(user);
        return convertToDTO(saved);
    }
    
    public void deleteUser(Long userId) {
        User user = userRepo.getUser(userId);
        if (user != null) {
            userRepo.deleteUser(user);
        }
    }
    
    public UserResponseDTO modifyUser(Long userId, UserRequestDTO userDto) {
        User existing = userRepo.getUser(userId);
        if (existing != null) {
            // Actualizar solo los campos que vienen en el DTO
            if (userDto.getFirstName() != null) existing.setFirstName(userDto.getFirstName());
            if (userDto.getSecondName() != null) existing.setSecondName(userDto.getSecondName());
            if (userDto.getLastName() != null) existing.setLastName(userDto.getLastName());
            if (userDto.getEmail() != null) existing.setEmail(userDto.getEmail());
            if (userDto.getPhoneNumber() != null) existing.setPhoneNumber(userDto.getPhoneNumber());
            if (userDto.getPhotoUrl() != null) existing.setPhotoUrl(userDto.getPhotoUrl());
            if (userDto.getPassword() != null) existing.setPasswordHash(pwdHasher.hash(userDto.getPassword()));
            
            User updated = userRepo.modifyUser(existing);
            return convertToDTO(updated);
        }
        throw new RuntimeException("User not found with id: " + userId);
    }
    
    public UserResponseDTO getUser(Long userId) {
        User user = userRepo.getUser(userId);
        if (user != null) {
            return convertToDTO(user);
        }
        throw new RuntimeException("User not found with id: " + userId);
    }
    
    public List<UserResponseDTO> getAllUsers() {
        return userRepo.getAllUsers().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
}