package com.app.consMed.Modules.Disponibilidade.Controller;

import com.app.consMed.Modules.Disponibilidade.DTOs.CreateDisponibilidadeDTO;
import com.app.consMed.Modules.Disponibilidade.DTOs.UpdateDisponibilidadeDTO;
import com.app.consMed.Modules.Disponibilidade.Domain.Disponibilidade;
import com.app.consMed.Modules.Disponibilidade.Service.DisponibilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disponibilidades")
public class DisponibilidadeController {
    @Autowired
    private DisponibilidadeService disponibilidadeService;

    @GetMapping
    public List<Disponibilidade> findAll() {
        return disponibilidadeService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Disponibilidade> findById(@PathVariable Long id) {
        return disponibilidadeService.findById(id);
    }

    @GetMapping("/medico/{medicoId}")
    public List<Disponibilidade> findByMedico(@PathVariable Long medicoId) {
        return disponibilidadeService.findByMedico(medicoId);
    }

    @PostMapping
    public Disponibilidade create(@RequestBody CreateDisponibilidadeDTO dto) {
        return disponibilidadeService.registerDisponibilidade(dto);
    }

    @PutMapping("/{id}")
    public Disponibilidade update(@PathVariable Long id, @RequestBody UpdateDisponibilidadeDTO dto) {
        return disponibilidadeService.updateDisponibilidade(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        disponibilidadeService.deleteDisponibilidade(id);
    }
}
