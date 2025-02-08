package com.app.consMed.Modules.Paciente.Domain;

import com.app.consMed.Modules.Utils.Contato;
import jakarta.persistence.*;

@Entity(name = "paciente")
@Table(name = "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    @Embedded
    private Contato contato;

    public Paciente() {
    }

    public Paciente(String nome, String cpf, Contato contato) {
        this.nome = nome;
        this.cpf = cpf;
        this.contato = contato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}
