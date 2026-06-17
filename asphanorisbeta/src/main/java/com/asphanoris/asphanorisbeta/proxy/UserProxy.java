package com.asphanoris.asphanorisbeta.proxy;

import com.asphanoris.asphanorisbeta.domain.User;
import com.asphanoris.asphanorisbeta.dto.UserRequestDTO;
import com.asphanoris.asphanorisbeta.dto.UserResponseDTO;
import com.asphanoris.asphanorisbeta.service.UserService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserProxy {
    
    private final UserService userService;
    private User currentUser;
    
    // ✅ Inyección por constructor
    public UserProxy(UserService userService) {
        this.userService = userService;
    }
    
    private boolean isAllowed() {
        return currentUser != null && 
               (currentUser.getRole().toString().equals("ADMIN") ||
                currentUser.getId().equals(currentUser.getId()));
    }
    
    public UserResponseDTO addUser(UserRequestDTO userDto) {
        if (!isAllowed()) {
            throw new SecurityException("Not authorized to add users");
        }
        return userService.addUser(userDto);
    }
    
    public void deleteUser(Long userId) {
        if (!isAllowed()) {
            throw new SecurityException("Not authorized to delete users");
        }
        userService.deleteUser(userId);
    }
    
    public UserResponseDTO modifyUser(Long userId, UserRequestDTO userDto) {
        if (!isAllowed()) {
            throw new SecurityException("Not authorized to modify users");
        }
        return userService.modifyUser(userId, userDto);
    }
    
    public UserResponseDTO getUser(Long userId) {
        if (!isAllowed()) {
            throw new SecurityException("Not authorized to get user info");
        }
        return userService.getUser(userId);
    }
    
    public List<UserResponseDTO> getAllUsers() {
        if (!isAllowed()) {
            throw new SecurityException("Not authorized to get all users");
        }
        return userService.getAllUsers();
    }
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}