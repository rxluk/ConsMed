package com.app.consMed.Modules.Admin.DTOs;

import com.app.consMed.Modules.User.Enums.UserRole;

public record DetailAdminDTO(Long id, String nome, String cpf, String Login, UserRole role) {
}
