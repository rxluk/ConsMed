package com.app.consMed.Modules.Disponibilidade.Controller;

import com.app.consMed.Modules.Disponibilidade.DTOs.CreateDisponibilidadeDTO;
import com.app.consMed.Modules.Disponibilidade.DTOs.UpdateDisponibilidadeDTO;
import com.app.consMed.Modules.Disponibilidade.Domain.Disponibilidade;
import com.app.consMed.Modules.Disponibilidade.Service.DisponibilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disponibilidades")
public class DisponibilidadeController extends BaseDisponibilidadeController implements DisponibilidadeDocumentation {

    @Autowired
    private DisponibilidadeService disponibilidadeService;

    @Override
    public ResponseEntity<Object> findAll() {
        List<Disponibilidade> disponibilidades = disponibilidadeService.findAll();
        return disponibilidades.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(disponibilidades);
    }

    @Override
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Disponibilidade> disponibilidade = disponibilidadeService.findById(id);
        return disponibilidade.isPresent() ? ResponseEntity.ok(disponibilidade.get()) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Object> findByMedico(@PathVariable Long medicoId) {
        List<Disponibilidade> disponibilidades = disponibilidadeService.findByMedico(medicoId);
        return disponibilidades.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(disponibilidades);
    }

    @Override
    public ResponseEntity<Object> create(@RequestBody CreateDisponibilidadeDTO dto) {
        Disponibilidade disponibilidade = disponibilidadeService.registerDisponibilidade(dto);
        return ResponseEntity.status(201).body(disponibilidade);
    }

    @Override
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody UpdateDisponibilidadeDTO dto) {
        Disponibilidade updatedDisponibilidade = disponibilidadeService.updateDisponibilidade(id, dto);
        return ResponseEntity.ok(updatedDisponibilidade);
    }

    @Override
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        disponibilidadeService.deleteDisponibilidade(id);
        return ResponseEntity.noContent().build();
    }
}