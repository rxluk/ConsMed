package com.app.consMed.Modules.Consulta.DTOs;

import java.util.Date;

public record ConsultaResponseDTO(
        Long id,
        Date data,
        boolean status,
        boolean pagamento,
        String nomeMedico,
        String nomePaciente
) {}