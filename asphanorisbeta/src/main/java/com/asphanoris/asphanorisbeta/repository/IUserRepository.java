package com.asphanoris.asphanorisbeta.repository;

import com.asphanoris.asphanorisbeta.domain.User;
import java.util.List;

public interface IUserRepository {
    User addUser(User user);
    User modifyUser(User user);
    void deleteUser(User user);
    User getUser(Long userId);
    List<User> getAllUsers();
}