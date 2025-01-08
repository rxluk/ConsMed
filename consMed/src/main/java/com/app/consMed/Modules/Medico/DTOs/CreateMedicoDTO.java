package com.app.consMed.Modules.Medico.DTOs;

import com.app.consMed.Modules.Medico.Enums.Especialidade;
import com.app.consMed.Modules.User.Enums.UserRole;

public record CreateMedicoDTO(
        String nome, String cpf, String crm, Especialidade especialidade,
        String estado, String cidade, String bairro, String rua, int numero, String telefone,
        String login, String password, UserRole role) {
}
