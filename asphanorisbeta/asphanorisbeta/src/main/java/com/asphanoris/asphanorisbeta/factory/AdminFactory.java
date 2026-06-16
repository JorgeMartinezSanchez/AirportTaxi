package com.asphanoris.asphanorisbeta.factory;

import com.asphanoris.asphanorisbeta.domain.Admin;
import com.asphanoris.asphanorisbeta.domain.User;
import com.asphanoris.asphanorisbeta.dto.UserRequestDTO;

public class AdminFactory implements UserFactory {
    @Override
    public User createUser(UserRequestDTO newUser) {
        Admin admin = new Admin();
        admin.setFirstName(newUser.getFirstName());
        admin.setSecondName(newUser.getSecondName());
        admin.setLastName(newUser.getLastName());
        admin.setEmail(newUser.getEmail());
        admin.setPhoneNumber(newUser.getPhoneNumber());
        admin.setPhotoUrl(newUser.getPhotoUrl());
        admin.setRole(newUser.getRole());
        admin.setAdminRole("SUPER_ADMIN");
        return admin;
    }
}