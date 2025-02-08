package com.app.consMed.Modules.Consulta.Controller;

import com.app.consMed.Modules.Consulta.DTOs.CreateConsultaDTO;
import com.app.consMed.Modules.Consulta.DTOs.UpdateConsultaDTO;
import com.app.consMed.Modules.Consulta.DTOs.ConsultaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface ConsultaDocumentation {

    @Operation(summary = "Busca uma consulta pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta encontrada"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    })
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    ResponseEntity<ConsultaResponseDTO> buscarConsultaPorId(@PathVariable Long id);

    @Operation(summary = "Busca todas as consultas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de consultas retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma consulta encontrada")
    })
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    ResponseEntity<List<ConsultaResponseDTO>> buscarTodasConsultas();

    @Operation(summary = "Cria uma nova consulta", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Consulta criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    ResponseEntity<ConsultaResponseDTO> criarConsulta(@RequestBody CreateConsultaDTO request);

    @Operation(summary = "Atualiza uma consulta pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    ResponseEntity<ConsultaResponseDTO> atualizarConsulta(@PathVariable Long id, @RequestBody UpdateConsultaDTO request);

    @Operation(summary = "Deleta uma consulta pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Consulta deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    })
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deletarConsulta(@PathVariable Long id);
}