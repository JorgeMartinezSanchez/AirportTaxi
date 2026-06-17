package com.asphanoris.asphanorisbeta.service;

import com.asphanoris.asphanorisbeta.domain.User;
import com.asphanoris.asphanorisbeta.dto.UserRequestDTO;
import com.asphanoris.asphanorisbeta.dto.UserResponseDTO;
import com.asphanoris.asphanorisbeta.factory.*;
import com.asphanoris.asphanorisbeta.repository.IUserRepository;
import com.asphanoris.asphanorisbeta.security.PasswordHasherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {
    
    @Autowired
    private IUserRepository userRepo;
    
    @Autowired
    private PasswordHasherService pwdHasher;
    
    private UserFactory getUserFactory(UserRequestDTO userDto) {
        log.debug("Obteniendo factory para rol: {}", userDto.getRole());
        switch (userDto.getRole()) {
            case ADMIN:
                return new AdminFactory();
            case DRIVER:
                return new DriverFactory();
            case PASSENGER:
                return new PassengerFactory();
            default:
                log.error("Rol inválido: {}", userDto.getRole());
                throw new IllegalArgumentException("Invalid role: " + userDto.getRole());
        }
    }
    
    private UserResponseDTO convertToDTO(User user) {
        log.debug("Mapeando usuario ID: {} a DTO", user.getId());
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
        log.info("Creando nuevo usuario con email: {} y rol: {}", userDto.getEmail(), userDto.getRole());
        try {
            UserFactory factory = getUserFactory(userDto);
            User user = factory.createUser(userDto);
            user.setPasswordHash(pwdHasher.hash(userDto.getPassword()));
            log.debug("Usuario creado en memoria, hasheando contraseña");
            
            User saved = userRepo.addUser(user);
            log.info("Usuario creado exitosamente con ID: {} y email: {}", saved.getId(), saved.getEmail());
            return convertToDTO(saved);
        } catch (Exception e) {
            log.error("Error al crear usuario con email: {}", userDto.getEmail(), e);
            throw e;
        }
    }
    
    public void deleteUser(Long userId) {
        log.warn("Intentando eliminar usuario con ID: {}", userId);
        User user = userRepo.getUser(userId);
        if (user != null) {
            userRepo.deleteUser(user);
            log.info("Usuario ID: {} eliminado exitosamente", userId);
        } else {
            log.error("Usuario no encontrado con ID: {}", userId);
            throw new RuntimeException("User not found with id: " + userId);
        }
    }
    
    public UserResponseDTO modifyUser(Long userId, UserRequestDTO userDto) {
        log.info("Modificando usuario ID: {}", userId);
        User existing = userRepo.getUser(userId);
        if (existing != null) {
            log.debug("Usuario encontrado, actualizando campos");
            
            if (userDto.getFirstName() != null) {
                existing.setFirstName(userDto.getFirstName());
                log.debug("Campo firstName actualizado");
            }
            if (userDto.getSecondName() != null) {
                existing.setSecondName(userDto.getSecondName());
                log.debug("Campo secondName actualizado");
            }
            if (userDto.getLastName() != null) {
                existing.setLastName(userDto.getLastName());
                log.debug("Campo lastName actualizado");
            }
            if (userDto.getEmail() != null) {
                existing.setEmail(userDto.getEmail());
                log.debug("Campo email actualizado a: {}", userDto.getEmail());
            }
            if (userDto.getPhoneNumber() != null) {
                existing.setPhoneNumber(userDto.getPhoneNumber());
                log.debug("Campo phoneNumber actualizado");
            }
            if (userDto.getPhotoUrl() != null) {
                existing.setPhotoUrl(userDto.getPhotoUrl());
                log.debug("Campo photoUrl actualizado");
            }
            if (userDto.getPassword() != null) {
                existing.setPasswordHash(pwdHasher.hash(userDto.getPassword()));
                log.debug("Contraseña actualizada");
            }
            
            User updated = userRepo.modifyUser(existing);
            log.info("Usuario ID: {} modificado exitosamente", userId);
            return convertToDTO(updated);
        }
        log.error("Usuario no encontrado con ID: {}", userId);
        throw new RuntimeException("User not found with id: " + userId);
    }
    
    public UserResponseDTO getUser(Long userId) {
        log.debug("Buscando usuario con ID: {}", userId);
        User user = userRepo.getUser(userId);
        if (user != null) {
            log.debug("Usuario encontrado: {}", user.getEmail());
            return convertToDTO(user);
        }
        log.warn("Usuario no encontrado con ID: {}", userId);
        throw new RuntimeException("User not found with id: " + userId);
    }
    
    public List<UserResponseDTO> getAllUsers() {
        log.info("Obteniendo todos los usuarios");
        List<UserResponseDTO> users = userRepo.getAllUsers().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        log.info("Total de usuarios obtenidos: {}", users.size());
        return users;
    }
}