package com.app.consMed.Modules.Disponibilidade.Controller;

import com.app.consMed.Modules.Disponibilidade.DTOs.CreateDisponibilidadeDTO;
import com.app.consMed.Modules.Disponibilidade.DTOs.UpdateDisponibilidadeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface DisponibilidadeDocumentation {

    @Operation(summary = "Busca todas as disponibilidades", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de disponibilidades retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma disponibilidade encontrada")
    })
    @GetMapping
    ResponseEntity<Object> findAll();

    @Operation(summary = "Busca uma disponibilidade pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disponibilidade encontrada"),
            @ApiResponse(responseCode = "404", description = "Disponibilidade não encontrada")
    })
    @GetMapping("/{id}")
    ResponseEntity<Object> findById(@PathVariable Long id);

    @Operation(summary = "Busca as disponibilidades de um médico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de disponibilidades do médico"),
            @ApiResponse(responseCode = "204", description = "Nenhuma disponibilidade encontrada para o médico")
    })
    @GetMapping("/medico/{medicoId}")
    ResponseEntity<Object> findByMedico(@PathVariable Long medicoId);

    @Operation(summary = "Cria uma nova disponibilidade", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Disponibilidade criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    ResponseEntity<Object> create(@RequestBody CreateDisponibilidadeDTO dto);

    @Operation(summary = "Atualiza uma disponibilidade", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disponibilidade atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Disponibilidade não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PutMapping("/{id}")
    ResponseEntity<Object> update(@PathVariable Long id, @RequestBody UpdateDisponibilidadeDTO dto);

    @Operation(summary = "Deleta uma disponibilidade", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Disponibilidade deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Disponibilidade não encontrada")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Object> delete(@PathVariable Long id);
}