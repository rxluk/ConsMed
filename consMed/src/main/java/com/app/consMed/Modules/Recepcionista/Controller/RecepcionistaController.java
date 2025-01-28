package com.app.consMed.Modules.Recepcionista.Controller;

import com.app.consMed.Modules.Recepcionista.DTOs.CreateRecepcionistaDTO;
import com.app.consMed.Modules.Recepcionista.DTOs.DetailRecepcionistaDTO;
import com.app.consMed.Modules.Recepcionista.Domain.Recepcionista;
import com.app.consMed.Modules.Recepcionista.Service.RecepcionistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RecepcionistaController extends BaseRecepcionistaController implements RecepcionistaDocumentation {
    @Autowired
    private RecepcionistaService recepcionistaService;

    @GetMapping("/get/{cpf}")
    public ResponseEntity<Object> buscarRecepcionistaByCpf(@PathVariable String cpf) {
        Optional<Recepcionista> recepcionista = recepcionistaService.findByCpf(cpf);
        if(recepcionista.isPresent()) {
            DetailRecepcionistaDTO response = new DetailRecepcionistaDTO(
                    recepcionista.get().getNome(), recepcionista.get().getCpf(),
                    recepcionista.get().getContato().getEstado(), recepcionista.get().getContato().getCidade(), recepcionista.get().getContato().getBairro(),
                    recepcionista.get().getContato().getRua(), recepcionista.get().getContato().getNumero(), recepcionista.get().getContato().getTelefone(),
                    recepcionista.get().getUser().getLogin(), recepcionista.get().getUser().getRole()
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> buscarTodosRecepcionista() {
        try {
            List<Recepcionista> recepcionistas = recepcionistaService.findAll();
            List<DetailRecepcionistaDTO> response = new ArrayList<>();
            if(!recepcionistas.isEmpty()) {
                for(Recepcionista recepcionista : recepcionistas) {
                    response.add(new DetailRecepcionistaDTO(
                            recepcionista.getNome(), recepcionista.getCpf(),
                            recepcionista.getContato().getEstado(), recepcionista.getContato().getCidade(), recepcionista.getContato().getBairro(),
                            recepcionista.getContato().getRua(), recepcionista.getContato().getNumero(), recepcionista.getContato().getTelefone(),
                            recepcionista.getUser().getLogin(), recepcionista.getUser().getRole()
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
    public ResponseEntity<Object> deletarAdminByCpf(@PathVariable String cpf) {
        if(recepcionistaService.findByCpf(cpf).isEmpty()) return ResponseEntity.notFound().build();
        recepcionistaService.deleteRecepcionistaByCpf(cpf);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/update/{cpf}")
    public ResponseEntity<Object> atualizarRecepcionistaByCpf(@PathVariable String cpf, @RequestBody CreateRecepcionistaDTO request) {
        if(recepcionistaService.findByCpf(cpf).isEmpty())
            return ResponseEntity.notFound().build();
        Recepcionista updateRecepcionista = recepcionistaService.updateRecepcionistaByCpf(cpf, request);
        if(updateRecepcionista != null) {
            DetailRecepcionistaDTO response = new DetailRecepcionistaDTO(
                    updateRecepcionista.getNome(), updateRecepcionista.getCpf(),
                    updateRecepcionista.getContato().getEstado(), updateRecepcionista.getContato().getCidade(), updateRecepcionista.getContato().getBairro(),
                    updateRecepcionista.getContato().getRua(), updateRecepcionista.getContato().getNumero(), updateRecepcionista.getContato().getTelefone(),
                    updateRecepcionista.getUser().getLogin(), updateRecepcionista.getUser().getRole()
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }
}
