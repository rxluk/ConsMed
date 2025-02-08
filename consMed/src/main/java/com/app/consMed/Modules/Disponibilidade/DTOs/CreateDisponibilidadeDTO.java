package com.app.consMed.Modules.Disponibilidade.DTOs;

import java.util.Date;

public record CreateDisponibilidadeDTO(Date data, boolean disponivel, Long medicoId) {
}
