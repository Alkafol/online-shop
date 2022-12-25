package com.alkafol.securitymicroservice.dto;

import com.alkafol.securitymicroservice.models.Role;
import com.alkafol.securitymicroservice.models.User;

public class Mapper {
    public static User mapToUser(UserPostDto userPostDto) {
        return new User(
                userPostDto.getUsername(),
                userPostDto.getEmail(),
                userPostDto.getPassword(),
                userPostDto.getRole()
        );
    }

    public static UserGetDto mapToUserGetDto (User user){
        return new UserGetDto(
                user.getUsername(),
                user.getEmail(),
                user.getBalance(),
                user.getStatus(),
                user.getRole()
        );
    }
}
