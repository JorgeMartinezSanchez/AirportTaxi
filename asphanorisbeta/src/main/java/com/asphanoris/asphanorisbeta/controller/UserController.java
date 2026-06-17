package com.asphanoris.asphanorisbeta.controller;

import com.asphanoris.asphanorisbeta.dto.UserRequestDTO;
import com.asphanoris.asphanorisbeta.dto.UserResponseDTO;
import com.asphanoris.asphanorisbeta.proxy.UserProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserProxy userProxy;
    
    // ✅ Inyección por constructor
    public UserController(UserProxy userProxy) {
        this.userProxy = userProxy;
    }
    
    @PostMapping
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userDto) {
        return ResponseEntity.ok(userProxy.addUser(userDto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userProxy.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> modifyUser(
            @PathVariable Long id,
            @RequestBody UserRequestDTO userDto) {
        return ResponseEntity.ok(userProxy.modifyUser(id, userDto));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userProxy.getUser(id));
    }
    
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userProxy.getAllUsers());
    }
}