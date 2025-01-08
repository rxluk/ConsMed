package com.app.consMed.Modules.Recepcionista.Repository;

import com.app.consMed.Modules.Recepcionista.Domain.Recepcionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Long> {

    Optional<Recepcionista> findByCpf(String cpf);
    List<Recepcionista> findAll();
}
