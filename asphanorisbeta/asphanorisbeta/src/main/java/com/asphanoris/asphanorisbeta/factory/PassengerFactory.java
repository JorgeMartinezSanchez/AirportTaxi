package com.asphanoris.asphanorisbeta.factory;

import com.asphanoris.asphanorisbeta.domain.ConPassenger;
import com.asphanoris.asphanorisbeta.domain.User;
import com.asphanoris.asphanorisbeta.dto.UserRequestDTO;

public class PassengerFactory implements UserFactory {
    @Override
    public User createUser(UserRequestDTO newUser) {
        ConPassenger passenger = new ConPassenger();
        passenger.setFirstName(newUser.getFirstName());
        passenger.setSecondName(newUser.getSecondName());
        passenger.setLastName(newUser.getLastName());
        passenger.setEmail(newUser.getEmail());
        passenger.setPhoneNumber(newUser.getPhoneNumber());
        passenger.setPhotoUrl(newUser.getPhotoUrl());
        passenger.setRole(newUser.getRole());
        return passenger;
    }
}