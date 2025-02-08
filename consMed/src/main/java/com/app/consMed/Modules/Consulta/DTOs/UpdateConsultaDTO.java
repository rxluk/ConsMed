package com.app.consMed.Modules.Consulta.DTOs;

import java.util.Date;

public record UpdateConsultaDTO(
        Date data,
        boolean status,
        boolean pagamento
) {}