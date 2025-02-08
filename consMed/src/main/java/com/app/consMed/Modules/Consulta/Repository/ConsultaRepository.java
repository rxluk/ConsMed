package com.app.consMed.Modules.Consulta.Repository;

import com.app.consMed.Modules.Consulta.Domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
