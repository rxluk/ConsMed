package com.app.consMed.Modules.Consulta.Domain;

import com.app.consMed.Modules.Medico.Domain.Medico;
import com.app.consMed.Modules.Paciente.Domain.Paciente;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Date;

@Entity(name = "consulta")
@Table(name = "consulta")
public class Consulta {
    @Id
    private Long id;
    private Date data;
    private boolean status;
    private boolean pagamento;
    private Date dataCriacao;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public Consulta() {
    }

    public Consulta(Date data, boolean status, boolean pagamento, Medico medico, Paciente paciente) {
        this.data = data;
        this.status = status;
        this.pagamento = pagamento;
        this.medico = medico;
        this.paciente = paciente;
        this.dataCriacao = Date.from(Instant.now());
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isPagamento() {
        return pagamento;
    }

    public void setPagamento(boolean pagamento) {
        this.pagamento = pagamento;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
