package com.app.consMed.Modules.Consulta.Controller;

import com.app.consMed.Modules.Consulta.DTOs.CreateConsultaDTO;
import com.app.consMed.Modules.Consulta.DTOs.UpdateConsultaDTO;
import com.app.consMed.Modules.Consulta.DTOs.ConsultaResponseDTO;
import com.app.consMed.Modules.Consulta.Service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController extends BaseConsultaController implements ConsultaDocumentation {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @Override
    public ResponseEntity<ConsultaResponseDTO> buscarConsultaPorId(@PathVariable Long id) {
        Optional<ConsultaResponseDTO> consulta = consultaService.findById(id);
        return consulta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(new ConsultaResponseDTO(
                        null, // id
                        null, // data
                        false, // status
                        false, // pagamento
                        "", // nomeMedico
                        "" // nomePaciente
                )));
    }

    @Override
    public ResponseEntity<List<ConsultaResponseDTO>> buscarTodasConsultas() {
        List<ConsultaResponseDTO> consultas = consultaService.findAll();
        if (consultas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<ConsultaResponseDTO> criarConsulta(@RequestBody CreateConsultaDTO request) {
        try {
            ConsultaResponseDTO consulta = consultaService.createConsulta(request);
            return ResponseEntity.status(201).body(consulta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(new ConsultaResponseDTO(
                    null, // id
                    null, // data
                    false, // status
                    false, // pagamento
                    "", // nomeMedico
                    "" // nomePaciente
            ));
        }
    }

    @Override
    public ResponseEntity<ConsultaResponseDTO> atualizarConsulta(@PathVariable Long id, @RequestBody UpdateConsultaDTO request) {
        try {
            ConsultaResponseDTO consulta = consultaService.updateConsulta(id, request);
            return ResponseEntity.ok(consulta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(new ConsultaResponseDTO(
                    null, // id
                    null, // data
                    false, // status
                    false, // pagamento
                    "", // nomeMedico
                    "" // nomePaciente
            ));
        }
    }

    @Override
    public ResponseEntity<Void> deletarConsulta(@PathVariable Long id) {
        try {
            consultaService.deleteConsulta(id);
            return ResponseEntity.status(204).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).build();
        }
    }
}