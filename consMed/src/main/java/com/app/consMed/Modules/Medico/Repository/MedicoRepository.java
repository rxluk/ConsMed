package com.app.consMed.Modules.Medico.Repository;

import com.app.consMed.Modules.Medico.Domain.Medico;
import com.app.consMed.Modules.Medico.Enums.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Override
    Optional<Medico> findById(Long id);
    Optional<Medico> findByCrm(String crm);
    Optional<Medico> findByCpf(String cpf);
    List<Medico> findByEspecialidade(Especialidade especialidade);
}
