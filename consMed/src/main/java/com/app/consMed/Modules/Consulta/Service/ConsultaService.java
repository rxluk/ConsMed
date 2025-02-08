package com.app.consMed.Modules.Consulta.Service;

import com.app.consMed.Modules.Consulta.Domain.Consulta;
import com.app.consMed.Modules.Consulta.DTOs.CreateConsultaDTO;
import com.app.consMed.Modules.Consulta.DTOs.UpdateConsultaDTO;
import com.app.consMed.Modules.Consulta.DTOs.ConsultaResponseDTO;
import com.app.consMed.Modules.Consulta.Repository.ConsultaRepository;
import com.app.consMed.Modules.Medico.Repository.MedicoRepository;
import com.app.consMed.Modules.Paciente.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public ConsultaResponseDTO createConsulta(CreateConsultaDTO dto) {
        var medico = medicoRepository.findById(dto.medicoId())
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

        var paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        Consulta consulta = new Consulta(
                dto.data(),
                dto.status(),
                dto.pagamento(),
                medico,
                paciente
        );

        consultaRepository.save(consulta);

        return new ConsultaResponseDTO(
                consulta.getId(),
                consulta.getData(),
                consulta.isStatus(),
                consulta.isPagamento(),
                medico.getNome(),
                paciente.getNome()
        );
    }

    public List<ConsultaResponseDTO> findAll() {
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas.stream().map(consulta ->
                new ConsultaResponseDTO(
                        consulta.getId(),
                        consulta.getData(),
                        consulta.isStatus(),
                        consulta.isPagamento(),
                        consulta.getMedico().getNome(),
                        consulta.getPaciente().getNome()
                )
        ).collect(Collectors.toList());
    }

    public Optional<ConsultaResponseDTO> findById(Long id) {
        return consultaRepository.findById(id).map(consulta ->
                new ConsultaResponseDTO(
                        consulta.getId(),
                        consulta.getData(),
                        consulta.isStatus(),
                        consulta.isPagamento(),
                        consulta.getMedico().getNome(),
                        consulta.getPaciente().getNome()
                )
        );
    }

    public ConsultaResponseDTO updateConsulta(Long id, UpdateConsultaDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));

        consulta.setData(dto.data());
        consulta.setStatus(dto.status());
        consulta.setPagamento(dto.pagamento());

        consultaRepository.save(consulta);

        return new ConsultaResponseDTO(
                consulta.getId(),
                consulta.getData(),
                consulta.isStatus(),
                consulta.isPagamento(),
                consulta.getMedico().getNome(),
                consulta.getPaciente().getNome()
        );
    }

    public void deleteConsulta(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new IllegalArgumentException("Consulta não encontrada");
        }
        consultaRepository.deleteById(id);
    }
}