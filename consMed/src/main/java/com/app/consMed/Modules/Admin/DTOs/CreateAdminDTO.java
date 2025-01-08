package com.app.consMed.Modules.Admin.DTOs;

import com.app.consMed.Modules.User.Enums.UserRole;

public record CreateAdminDTO(String nome, String cpf, String login, String password, UserRole role) {
}
