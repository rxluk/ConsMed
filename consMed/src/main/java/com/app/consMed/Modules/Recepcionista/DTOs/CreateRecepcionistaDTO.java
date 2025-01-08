package com.app.consMed.Modules.Recepcionista.DTOs;

import com.app.consMed.Modules.User.Enums.UserRole;

public record CreateRecepcionistaDTO(String nome, String cpf,
                                     String estado, String cidade, String bairro, String rua, int numero, String telefone,
                                     String login, String password, UserRole role) {
}
