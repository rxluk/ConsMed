package com.app.consMed.Modules.User.DTOs;

import com.app.consMed.Modules.User.Enums.UserRole;

public record DetailUserDTO(String login, UserRole role) {
}
