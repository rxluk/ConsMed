package com.app.consMed.Modules.Paciente.Controller;

import com.app.consMed.Modules.Paciente.DTOs.CreatePacienteDTO;
import com.app.consMed.Modules.Paciente.DTOs.PacienteResponseDTO;
import com.app.consMed.Modules.Paciente.DTOs.UpdatePacienteDTO;
import com.app.consMed.Modules.Paciente.Domain.Paciente;
import com.app.consMed.Modules.Paciente.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PacienteController extends BasePacienteController implements PacienteDocumentation {

    @Autowired
    private PacienteService pacienteService;

    @Override
    @GetMapping("/get/{cpf}")
    public ResponseEntity<Object> buscarPacienteByCpf(@PathVariable String cpf) {
        Optional<Paciente> paciente = pacienteService.findByCpf(cpf);
        if (paciente.isPresent()) {
            PacienteResponseDTO response = new PacienteResponseDTO(
                    paciente.get().getId(), paciente.get().getNome(), paciente.get().getCpf(),
                    paciente.get().getContato().getEstado(), paciente.get().getContato().getCidade(),
                    paciente.get().getContato().getBairro(), paciente.get().getContato().getRua(),
                    paciente.get().getContato().getNumero(), paciente.get().getContato().getTelefone()
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @GetMapping("/get/all")
    public ResponseEntity<Object> buscarTodosPacientes() {
        try {
            List<Paciente> pacientes = pacienteService.findAll();
            List<PacienteResponseDTO> response = new ArrayList<>();
            if (!pacientes.isEmpty()) {
                for (Paciente paciente : pacientes) {
                    response.add(new PacienteResponseDTO(
                            paciente.getId(), paciente.getNome(), paciente.getCpf(),
                            paciente.getContato().getEstado(), paciente.getContato().getCidade(),
                            paciente.getContato().getBairro(), paciente.getContato().getRua(),
                            paciente.getContato().getNumero(), paciente.getContato().getTelefone()
                    ));
                }
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<Object> createPaciente(@RequestBody CreatePacienteDTO request) {
        try {
            PacienteResponseDTO response = pacienteService.registerPaciente(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @Transactional
    @DeleteMapping("/delete/{cpf}")
    public ResponseEntity<Object> deletePacienteByCpf(@PathVariable String cpf) {
        if (pacienteService.findByCpf(cpf).isEmpty()) return ResponseEntity.notFound().build();
        pacienteService.deletePacienteByCpf(cpf);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Transactional
    @PutMapping("/update/{cpf}")
    public ResponseEntity<Object> updatePacienteById(@PathVariable String cpf, @RequestBody UpdatePacienteDTO request) {
        try {
            PacienteResponseDTO response = pacienteService.updatePacienteByCpf(cpf, request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}