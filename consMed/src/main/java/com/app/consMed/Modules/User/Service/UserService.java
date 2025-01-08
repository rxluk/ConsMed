package com.app.consMed.Modules.User.Service;

import com.app.consMed.Config.SecurityConfig;
import com.app.consMed.Modules.User.DTOs.LoginResponseDTO;
import com.app.consMed.Modules.User.DTOs.CreateUserDTO;
import com.app.consMed.Modules.User.Domain.User;
import com.app.consMed.Modules.User.Repository.UserRepository;
import com.app.consMed.Security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SecurityConfig securityConfig;

    public User register(CreateUserDTO json) {
        if (userRepository.findByLogin(json.login()) != null) {
            throw new IllegalArgumentException("Login já existente!");
        }

        String encryptedPassword = securityConfig.passwordEncoder().encode(json.password());
        User newUser = new User(json.login(), encryptedPassword, json.role());
        return userRepository.save(newUser);
    }

    public LoginResponseDTO login(String login, String password) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
        );

        User user = (User) auth.getPrincipal();
        String token = tokenService.generateToken(user);

        return new LoginResponseDTO(token);
    }

    public Optional<User> findByLogin(String login) {
        return Optional.of((User) userRepository.findByLogin(login));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserByLogin(String login) {
        userRepository.deleteByLogin(login);
    }

    @Transactional
    public User updateUserByLogin(String login, CreateUserDTO json) {
        Optional<User> oldUser = Optional.of((User) userRepository.findByLogin(login));
        if (oldUser.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        if (!json.login().equals(oldUser.get().getLogin()) && userRepository.findByLogin(json.login()) != null) {
            throw new IllegalArgumentException("Login já existente!");
        }

        String encryptedPassword = securityConfig.passwordEncoder().encode(json.password());

        oldUser.get().setLogin(json.login());
        oldUser.get().setPassword(encryptedPassword);
        oldUser.get().setRole(json.role());

        return userRepository.save(oldUser.get());
    }
}
