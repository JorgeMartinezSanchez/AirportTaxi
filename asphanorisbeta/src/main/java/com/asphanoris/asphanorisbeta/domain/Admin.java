package com.asphanoris.asphanorisbeta.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Admin extends User {
    private String adminRole;
    
    public User addUser(User newUser) {
        User result;
        switch (newUser.role) {
            case PASSENGER:
                result = new ConPassenger();
                break;
            case DRIVER:
                result = new ConDriver();
                break;
            default:
                throw new IllegalStateException("Cannot create user with role: " + newUser.role);
        }
        result.copyFrom(newUser);
        return result;
    }
    
    public void deleteUser(Long userId) {
        // Lógica para eliminar usuario
    }
    
    public void banUser(Long userId) {
        // Lógica para banear usuario
    }
    
    public User modifyUser(User user) {
        return user.modifyUser(user);
    }
}