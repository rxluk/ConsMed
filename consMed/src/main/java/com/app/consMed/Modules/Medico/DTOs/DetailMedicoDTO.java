package com.app.consMed.Modules.Medico.DTOs;

import com.app.consMed.Modules.Medico.Enums.Especialidade;
import com.app.consMed.Modules.User.Enums.UserRole;

public record DetailMedicoDTO(
        String nome, String cpf, String crm, Especialidade especialidade,
        String estado, String cidade, String bairro, String rua, int numero, String telefone,
        String login, UserRole role) {
}
