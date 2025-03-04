package org.example.authorbook.service;


import org.example.authorbook.entity.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findByEmail(String email);
}
