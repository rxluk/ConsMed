package com.app.consMed.Modules.Disponibilidade.Domain;

import com.app.consMed.Modules.Medico.Domain.Medico;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "disponibilidade")
@Table(name = "disponibilidade")
public class Disponibilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date data;
    private boolean disponivel;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    public Disponibilidade() {
    }

    public Disponibilidade(Date data, boolean disponivel, Medico medico) {
        this.data = data;
        this.disponivel = disponivel;
        this.medico = medico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
