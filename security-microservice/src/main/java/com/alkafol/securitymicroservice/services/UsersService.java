package com.alkafol.securitymicroservice.services;

import com.alkafol.securitymicroservice.config.JwtService;
import com.alkafol.securitymicroservice.dto.*;
import com.alkafol.securitymicroservice.models.User;
import com.alkafol.securitymicroservice.models.UserStatus;
import com.alkafol.securitymicroservice.repositories.UsersRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.NoSuchElementException;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public String register(UserPostDto userPostDto) {
        if (usersRepository.existsById(userPostDto.getUsername())){
            throw new RuntimeException("Username already taken");
        }

        User user = Mapper.mapToUser(userPostDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);

        return jwtService.generateToken(new HashMap<>() {{
            put("role", user.getRole());
        }}, user);
    }

    public String authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        User user = usersRepository.findByUsername(authRequest.getUsername()).orElseThrow(NoSuchElementException::new);
        return jwtService.generateToken(new HashMap<>() {{
            put("role", user.getRole());
        }}, user);
    }

    public AuthResponse validateUserToken(String token) {
        return new AuthResponse(
                String.valueOf(jwtService.isUserTokenValid(token)),
                jwtService.getUsername(token));
    }

    public AuthResponse validateAdminToken(String token) {
        return new AuthResponse(
                String.valueOf(jwtService.isAdminTokenValid(token)),
                jwtService.getUsername(token));
    }

    public void addMoney(String username, double amount){
        User user = usersRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        if (amount <= 0){
            throw new RuntimeException("Invalid amount");
        }

        user.setBalance(user.getBalance() + amount);
        usersRepository.save(user);
    }

    public void takeMoney(String username, double amount){
        User user = usersRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        if (amount <= 0 || user.getBalance() - amount < 0){
            throw new RuntimeException("Invalid amount");
        }

        user.setBalance(user.getBalance() - amount);
        usersRepository.save(user);
    }

    public void changeUserStatus(String username, UserStatus status){
        User user = usersRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        user.setStatus(status);
        usersRepository.save(user);
    }

    public UserGetDto viewUserInfo(String username) {
        User user = usersRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        return Mapper.mapToUserGetDto(user);
    }
}
