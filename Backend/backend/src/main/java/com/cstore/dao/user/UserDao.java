package com.cstore.dao.user;

import com.cstore.model.user.RegisteredUser;
import com.cstore.model.user.User;

import java.util.Optional;

public interface UserDao {
    Optional<RegisteredUser> findUserByEmail(String email);

    User saveUser(User user);

    RegisteredUser saveRegisteredUser(RegisteredUser registeredUser);
}
