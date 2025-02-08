package com.app.consMed.Modules.Consulta.DTOs;

import java.util.Date;

public record CreateConsultaDTO(
        Date data,
        boolean status,
        boolean pagamento,
        Long medicoId,
        Long pacienteId
) {}
