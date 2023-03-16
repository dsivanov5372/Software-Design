package com.example.loginserver.service;

import com.example.loginserver.exception.InvalidPasswordException;
import com.example.loginserver.exception.UserAlreadyRegisteredException;
import com.example.loginserver.exception.UserNotFoundException;
import com.example.loginserver.model.User;
import com.example.loginserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UserRepository repository;

    public void register(User user) {
        if (repository.findByLogin(user.getLogin()).isPresent()) {
            throw new UserAlreadyRegisteredException("User already registered!");
        }

        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
    }

    public void login(User user) {
        User result = repository.findByLogin(user.getLogin())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
        if (!encoder.matches(user.getPassword(), result.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
    }
}