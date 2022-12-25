package com.alkafol.securitymicroservice.controllers;

import com.alkafol.securitymicroservice.dto.AuthRequest;
import com.alkafol.securitymicroservice.dto.AuthResponse;
import com.alkafol.securitymicroservice.dto.UserGetDto;
import com.alkafol.securitymicroservice.dto.UserPostDto;
import com.alkafol.securitymicroservice.models.UserStatus;
import com.alkafol.securitymicroservice.services.UsersService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserPostDto userPostDto){
        return usersService.register(userPostDto);
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest){
        return usersService.authenticate(authRequest);
    }

    @GetMapping("/validate_user_token/{token}")
    public AuthResponse validateUserToken(@PathVariable String token){
        return usersService.validateUserToken(token);
    }

    @GetMapping("/validate_admin_token/{token}")
    public AuthResponse validateAdminToken(@PathVariable String token){
        return usersService.validateAdminToken(token);
    }

    @PutMapping("/add_money/{username}/{amount}")
    public void addMoney(@PathVariable String username, @PathVariable double amount){
        usersService.addMoney(username, amount);
    }

    @PutMapping("/take_money/{username}/{amount}")
    public void takeMoney(@PathVariable String username, @PathVariable double amount){
        usersService.takeMoney(username, amount);
    }

    @PutMapping("/change_status/{username}/{status}")
    public void changeUserStatus(@PathVariable String username, @PathVariable UserStatus status){
        usersService.changeUserStatus(username, status);
    }

    @GetMapping("/view_info/{username}")
    public UserGetDto viewUserInfo(@PathVariable String username){
        return usersService.viewUserInfo(username);
    }
}
