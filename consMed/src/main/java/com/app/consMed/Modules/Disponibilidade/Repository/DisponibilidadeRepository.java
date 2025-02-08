package com.app.consMed.Modules.Disponibilidade.Repository;

import com.app.consMed.Modules.Disponibilidade.Domain.Disponibilidade;
import com.app.consMed.Modules.Medico.Domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {
    List<Disponibilidade> findByMedico(Medico medico);
}
