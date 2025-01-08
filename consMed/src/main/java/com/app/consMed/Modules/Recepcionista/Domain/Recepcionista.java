package com.app.consMed.Modules.Recepcionista.Domain;

import com.app.consMed.Modules.User.Domain.User;
import com.app.consMed.Modules.User.Enums.UserRole;
import com.app.consMed.Modules.Utils.Contato;
import jakarta.persistence.*;

@Entity(name = "recepcionista")
@Table(name = "recepcionista")
public class Recepcionista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    @Embedded
    private Contato contato;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Recepcionista() {
    }

    public Recepcionista(String nome, String cpf, Contato contato, User user) {
        this.nome = nome;
        this.cpf = cpf;
        this.contato = contato;
        this.user = user;
    }

    public Recepcionista(String nome, String cpf,
                         String estado, String cidade, String bairro, String rua, int numero, String telefone,
                         String login, String password, UserRole role) {
        this.nome = nome;
        this.cpf = cpf;
        this.contato = new Contato(estado, cidade, bairro, rua, numero, telefone);
        this.user = new User(login, password, role);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
