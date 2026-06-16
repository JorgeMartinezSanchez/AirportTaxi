package com.asphanoris.asphanorisbeta.factory;

import com.asphanoris.asphanorisbeta.domain.User;
import com.asphanoris.asphanorisbeta.dto.UserRequestDTO;

public interface UserFactory {
    User createUser(UserRequestDTO newUser);
}