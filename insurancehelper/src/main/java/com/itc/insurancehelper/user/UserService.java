package com.itc.insurancehelper.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // generate user registration method
    // that takes username, email and raw password
    // checks if username or email already exists
    // if they do, throw IllegalArgumentException
    // if they don't, create a new user with the given details
    // encode the password using the passwordEncoder
    // assign the role USER to the new user
    // save the user to the repository and return the saved user
    public UserEntity register(String username, String email, String rawPassword) {
        if (userRepository.existsByUsername(username)) throw new IllegalArgumentException("Username already exists");
        if (userRepository.existsByEmail(email)) throw new IllegalArgumentException("Email already registered");
        var user = UserEntity.builder()
                .username(username)
                .email(email)
                .passwordHash(passwordEncoder.encode(rawPassword))
                .roles(Set.of(Role.USER))
                .build();
        return userRepository.save(user);
    }
}
