package com.app.consMed.Modules.Paciente.Controller;

import com.app.consMed.Modules.Paciente.DTOs.CreatePacienteDTO;
import com.app.consMed.Modules.Paciente.DTOs.UpdatePacienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PacienteDocumentation {

    @Operation(summary = "Busca um paciente pelo CPF", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @GetMapping("/get/{cpf}")
    ResponseEntity<Object> buscarPacienteByCpf(@PathVariable String cpf);

    @Operation(summary = "Busca todos os pacientes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pacientes retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum paciente encontrado")
    })
    @GetMapping("/get/all")
    ResponseEntity<Object> buscarTodosPacientes();

    @Operation(summary = "Cria um novo paciente", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping("/create")
    ResponseEntity<Object> createPaciente(@RequestBody CreatePacienteDTO request);

    @Operation(summary = "Deleta um paciente pelo CPF", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paciente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @DeleteMapping("/delete/{cpf}")
    ResponseEntity<Object> deletePacienteByCpf(@PathVariable String cpf);

    @Operation(summary = "Atualiza um paciente pelo CPF", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @PutMapping("/update/{cpf}")
    ResponseEntity<Object> updatePacienteById(@PathVariable String cpf, @RequestBody UpdatePacienteDTO request);
}