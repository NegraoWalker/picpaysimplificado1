package com.walker.picpaysimplificado1.dtos;

import com.walker.picpaysimplificado1.domain.user.UserType;

import java.math.BigDecimal;

public record UserDto(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
