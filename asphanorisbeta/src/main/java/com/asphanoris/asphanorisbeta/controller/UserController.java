package com.asphanoris.asphanorisbeta.controller;

import com.asphanoris.asphanorisbeta.dto.UserRequestDTO;
import com.asphanoris.asphanorisbeta.dto.UserResponseDTO;
import com.asphanoris.asphanorisbeta.proxy.UserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserProxy userProxy;
    
    @PostMapping
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userDto) {
        return ResponseEntity.ok(userProxy.addUser(userDto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // No necesitas crear un DTO, pasas el ID directamente
        userProxy.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> modifyUser(
            @PathVariable Long id,
            @RequestBody UserRequestDTO userDto) {
        // Pasas el ID de la URL y el DTO del body
        return ResponseEntity.ok(userProxy.modifyUser(id, userDto));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        // Pasas el ID directamente
        return ResponseEntity.ok(userProxy.getUser(id));
    }
    
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userProxy.getAllUsers());
    }
}