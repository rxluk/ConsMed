package com.app.consMed.Modules.Medico.Domain;

import com.app.consMed.Modules.Disponibilidade.Domain.Disponibilidade;
import com.app.consMed.Modules.Medico.Enums.Especialidade;
import com.app.consMed.Modules.User.Domain.User;
import com.app.consMed.Modules.User.Enums.UserRole;
import com.app.consMed.Modules.Utils.Contato;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "medico")
@Table(name = "medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Contato contato;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Disponibilidade> disponibilidades = new ArrayList<>();

    public Medico() {
    }

    public Medico(String nome, String cpf, String crm, Especialidade especialidade, Contato contato, User user) {
        this.nome = nome;
        this.cpf = cpf;
        this.crm = crm;
        this.especialidade = especialidade;
        this.contato = contato;
        this.user = user;
    }

    public Medico(String nome, String cpf, String crm, Especialidade especialidade,
                  String estado, String cidade, String bairro, String rua, int numero, String telefone,
                  String login, String password, UserRole role, List<Disponibilidade> disponibilidades) {
        this.nome = nome;
        this.cpf = cpf;
        this.crm = crm;
        this.especialidade = especialidade;
        this.contato = new Contato(estado, cidade, bairro, rua, numero, telefone);
        this.user = new User(login, password, role);
        this.disponibilidades = disponibilidades;
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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
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

    public List<Disponibilidade> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidades(List<Disponibilidade> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }
}
