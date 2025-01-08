package com.app.consMed.Modules.Admin.Domain;

import com.app.consMed.Modules.User.Domain.User;
import jakarta.persistence.*;

@Entity(name = "administrador")
@Table(name = "administrador")
public class Admin{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Admin() {
    }

    public Admin(Long id, String nome, String cpf, User user) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.user = user;
    }

    public Admin(String nome, String cpf, User user) {
        this.nome = nome;
        this.cpf = cpf;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
