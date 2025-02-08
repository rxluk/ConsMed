package com.app.consMed.Modules.Paciente.DTOs;

public record UpdatePacienteDTO(
        String nome, String cpf,
        String estado, String cidade, String bairro, String rua, int numero, String telefone) {
}
