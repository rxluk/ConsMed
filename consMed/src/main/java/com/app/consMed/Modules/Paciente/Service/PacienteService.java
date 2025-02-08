package com.app.consMed.Modules.Paciente.Service;

import com.app.consMed.Modules.Paciente.DTOs.CreatePacienteDTO;
import com.app.consMed.Modules.Paciente.DTOs.UpdatePacienteDTO;
import com.app.consMed.Modules.Paciente.DTOs.PacienteResponseDTO;
import com.app.consMed.Modules.Paciente.Domain.Paciente;
import com.app.consMed.Modules.Paciente.Repository.PacienteRepository;
import com.app.consMed.Modules.Utils.Contato;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    public Optional<Paciente> findByCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public PacienteResponseDTO registerPaciente(CreatePacienteDTO dto) {
        if (pacienteRepository.findByCpf(dto.cpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado!");
        }
        Contato contato = new Contato(dto.estado(), dto.cidade(), dto.bairro(), dto.rua(), dto.numero(), dto.telefone());
        Paciente paciente = new Paciente(dto.nome(), dto.cpf(), contato);
        paciente = pacienteRepository.save(paciente);
        return new PacienteResponseDTO(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getContato());
    }

    @Transactional
    public PacienteResponseDTO updatePacienteByCpf(String cpf, UpdatePacienteDTO dto) {
        Paciente paciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("CPF não cadastrado!"));

        paciente.setNome(dto.nome());
        paciente.setContato(new Contato(dto.estado(), dto.cidade(), dto.bairro(), dto.rua(), dto.numero(), dto.telefone()));

        paciente = pacienteRepository.save(paciente);
        return new PacienteResponseDTO(
                paciente.getId(), paciente.getNome(), paciente.getCpf(),
                paciente.getContato().getEstado(), paciente.getContato().getCidade(), paciente.getContato().getBairro(), paciente.getContato().getRua(),
                paciente.getContato().getNumero(), paciente.getContato().getTelefone());
    }

    @Transactional
    public void deletePacienteByCpf(String cpf) {
        Paciente paciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado!"));
        pacienteRepository.delete(paciente);
    }
}