package com.app.consMed.Modules.Medico.Controller;

import com.app.consMed.Modules.Medico.DTOs.CreateMedicoDTO;
import com.app.consMed.Modules.Medico.DTOs.DetailMedicoDTO;
import com.app.consMed.Modules.Medico.Domain.Medico;
import com.app.consMed.Modules.Medico.Enums.Especialidade;
import com.app.consMed.Modules.Medico.Service.MedicoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MedicoController extends BaseMedicoController {
    @Autowired
    private MedicoService medicoService;

    @GetMapping("/get/{cpf}")
    public ResponseEntity<Object> buscarMedicoByCpf(@PathVariable String cpf){
        Optional<Medico> medico = medicoService.findByCpf(cpf);
        if(medico.isPresent()){
            DetailMedicoDTO response = new DetailMedicoDTO(
                    medico.get().getNome(),medico.get().getCpf(), medico.get().getCrm(), medico.get().getEspecialidade(),
                    medico.get().getContato().getEstado(), medico.get().getContato().getCidade(), medico.get().getContato().getBairro(),
                    medico.get().getContato().getRua(), medico.get().getContato().getNumero(), medico.get().getContato().getTelefone(),
                    medico.get().getUser().getLogin(), medico.get().getUser().getRole()
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/get/{crm}")
    public ResponseEntity<Object> buscarMedicoByCrm(@PathVariable String crm){
        Optional<Medico> medico = medicoService.findByCrm(crm);
        if(medico.isPresent()){
            DetailMedicoDTO response = new DetailMedicoDTO(
                    medico.get().getNome(),medico.get().getCpf(), medico.get().getCrm(), medico.get().getEspecialidade(),
                    medico.get().getContato().getEstado(), medico.get().getContato().getCidade(), medico.get().getContato().getBairro(),
                    medico.get().getContato().getRua(), medico.get().getContato().getNumero(), medico.get().getContato().getTelefone(),
                    medico.get().getUser().getLogin(), medico.get().getUser().getRole()
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> buscarTodosMedicos(){
        try {
            List<Medico> medicos = medicoService.findAll();
            List<DetailMedicoDTO> response = new ArrayList<>();
            if(!medicos.isEmpty()) {
                for(Medico medico : medicos) {
                    response.add(new DetailMedicoDTO(
                            medico.getNome(),medico.getCpf(), medico.getCrm(), medico.getEspecialidade(),
                            medico.getContato().getEstado(), medico.getContato().getCidade(), medico.getContato().getBairro(),
                            medico.getContato().getRua(), medico.getContato().getNumero(), medico.getContato().getTelefone(),
                            medico.getUser().getLogin(), medico.getUser().getRole()
                    ));
                }
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("get/{especiadade}")
    public ResponseEntity<Object> buscarMedicosByEspeciadade(@PathVariable Especialidade especiadade){
        try {
            List<Medico> medicos = medicoService.findByEspecialidade(especiadade);
            List<DetailMedicoDTO> response = new ArrayList<>();
            if(!medicos.isEmpty()) {
                for(Medico medico : medicos) {
                    response.add(new DetailMedicoDTO(
                            medico.getNome(),medico.getCpf(), medico.getCrm(), medico.getEspecialidade(),
                            medico.getContato().getEstado(), medico.getContato().getCidade(), medico.getContato().getBairro(),
                            medico.getContato().getRua(), medico.getContato().getNumero(), medico.getContato().getTelefone(),
                            medico.getUser().getLogin(), medico.getUser().getRole()
                    ));
                }
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Transactional
    @DeleteMapping("/delete/{cpf}")
    public ResponseEntity<Object> deleteMedicoByCpf(@PathVariable String cpf) {
        if(!medicoService.findByCpf(cpf).isPresent()) return ResponseEntity.notFound().build();
        medicoService.deleteMedicoByCpf(cpf);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/update/{cpf}")
    public ResponseEntity<Object> updateMedicoById(@PathVariable String cpf, @RequestBody CreateMedicoDTO request) {
        if(!medicoService.findByCpf(cpf).isPresent())
            return ResponseEntity.notFound().build();
        Medico updateMedico = medicoService.updateMedicoByCpf(cpf, request);
        if(updateMedico != null) {
            DetailMedicoDTO response = new DetailMedicoDTO(
                    updateMedico.getNome(), updateMedico.getCpf(), updateMedico.getCrm(), updateMedico.getEspecialidade(),
                    updateMedico.getContato().getEstado(), updateMedico.getContato().getCidade(), updateMedico.getContato().getBairro(),
                    updateMedico.getContato().getRua(), updateMedico.getContato().getNumero(), updateMedico.getContato().getTelefone(),
                    updateMedico.getUser().getLogin(), updateMedico.getUser().getRole()
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }
}
