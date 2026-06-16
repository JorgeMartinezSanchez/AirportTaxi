package com.asphanoris.asphanorisbeta.factory;

import com.asphanoris.asphanorisbeta.domain.ConDriver;
import com.asphanoris.asphanorisbeta.domain.User;
import com.asphanoris.asphanorisbeta.dto.UserRequestDTO;

public class DriverFactory implements UserFactory {
    @Override
    public User createUser(UserRequestDTO newUser) {
        ConDriver driver = new ConDriver();
        driver.setFirstName(newUser.getFirstName());
        driver.setSecondName(newUser.getSecondName());
        driver.setLastName(newUser.getLastName());
        driver.setEmail(newUser.getEmail());
        driver.setPhoneNumber(newUser.getPhoneNumber());
        driver.setPhotoUrl(newUser.getPhotoUrl());
        driver.setRole(newUser.getRole());
        driver.setRate(5.0);
        return driver;
    }
}