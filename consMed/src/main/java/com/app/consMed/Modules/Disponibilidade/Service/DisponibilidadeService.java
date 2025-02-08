package com.app.consMed.Modules.Disponibilidade.Service;

import com.app.consMed.Modules.Disponibilidade.DTOs.CreateDisponibilidadeDTO;
import com.app.consMed.Modules.Disponibilidade.DTOs.UpdateDisponibilidadeDTO;
import com.app.consMed.Modules.Disponibilidade.Domain.Disponibilidade;
import com.app.consMed.Modules.Disponibilidade.Repository.DisponibilidadeRepository;
import com.app.consMed.Modules.Medico.Domain.Medico;
import com.app.consMed.Modules.Medico.Service.MedicoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisponibilidadeService {
    @Autowired
    private DisponibilidadeRepository disponibilidadeRepository;

    @Autowired
    private MedicoService medicoService;

    public List<Disponibilidade> findAll() {
        return disponibilidadeRepository.findAll();
    }

    public Optional<Disponibilidade> findById(Long id) {
        return disponibilidadeRepository.findById(id);
    }

    public List<Disponibilidade> findByMedico(Long medicoId) {
        Optional<Medico> medico = medicoService.findById(medicoId);
        if (medico.isEmpty()) {
            throw new IllegalArgumentException("Médico não encontrado!");
        }
        return disponibilidadeRepository.findByMedico(medico.get());
    }

    public Disponibilidade registerDisponibilidade(CreateDisponibilidadeDTO dto) {
        Optional<Medico> medico = medicoService.findById(dto.medicoId());
        if (medico.isEmpty()) {
            throw new IllegalArgumentException("Médico não encontrado!");
        }
        Disponibilidade disponibilidade = new Disponibilidade(dto.data(), dto.disponivel(), medico.get());
        return disponibilidadeRepository.save(disponibilidade);
    }

    @Transactional
    public Disponibilidade updateDisponibilidade(Long id, UpdateDisponibilidadeDTO dto) {
        Optional<Disponibilidade> disponibilidade = disponibilidadeRepository.findById(id);
        if (disponibilidade.isEmpty()) {
            throw new IllegalArgumentException("Disponibilidade não encontrada!");
        }
        disponibilidade.get().setData(dto.data());
        disponibilidade.get().setDisponivel(dto.disponivel());
        return disponibilidadeRepository.save(disponibilidade.get());
    }

    @Transactional
    public void deleteDisponibilidade(Long id) {
        Optional<Disponibilidade> disponibilidade = disponibilidadeRepository.findById(id);
        if (disponibilidade.isEmpty()) {
            throw new IllegalArgumentException("Disponibilidade não encontrada!");
        }
        disponibilidadeRepository.delete(disponibilidade.get());
    }
}
