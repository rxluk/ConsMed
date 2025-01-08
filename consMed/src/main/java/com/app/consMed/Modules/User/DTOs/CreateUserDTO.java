package com.app.consMed.Modules.User.DTOs;

import com.app.consMed.Modules.User.Enums.UserRole;

public record CreateUserDTO(String login, String password, UserRole role) {
}
