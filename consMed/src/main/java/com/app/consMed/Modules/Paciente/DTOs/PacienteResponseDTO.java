package com.app.consMed.Modules.Paciente.DTOs;

import com.app.consMed.Modules.Utils.Contato;

public class PacienteResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String telefone;

    public PacienteResponseDTO(Long id, String nome, String cpf, String estado, String cidade,
                               String bairro, String rua, int numero, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.telefone = telefone;
    }
}