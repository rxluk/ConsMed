package com.app.consMed.Modules.Disponibilidade.DTOs;

import com.app.consMed.Modules.Medico.Domain.Medico;

import java.util.Date;

public record DisponibilidadeDTO(Long id, Date data, boolean disponivel, Medico medico) {
}
