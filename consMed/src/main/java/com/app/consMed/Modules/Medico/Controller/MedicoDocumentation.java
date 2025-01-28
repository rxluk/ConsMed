package com.app.consMed.Modules.Medico.Controller;

import com.app.consMed.Modules.Medico.DTOs.CreateMedicoDTO;
import com.app.consMed.Modules.Medico.DTOs.DetailMedicoDTO;
import com.app.consMed.Modules.Medico.Enums.Especialidade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface MedicoDocumentation {

    @Operation(summary = "Busca um médico pelo CPF", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico encontrado"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    @RequestMapping(value = "/get/{cpf}", method = RequestMethod.GET)
    ResponseEntity<Object> buscarMedicoByCpf(@PathVariable String cpf);

    @Operation(summary = "Busca um médico pelo CRM", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico encontrado"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    @RequestMapping(value = "/get/{crm}", method = RequestMethod.GET)
    ResponseEntity<Object> buscarMedicoByCrm(@PathVariable String crm);

    @Operation(summary = "Busca todos os médicos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de médicos retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum médico encontrado")
    })
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    ResponseEntity<Object> buscarTodosMedicos();

    @Operation(summary = "Busca médicos por especialidade", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de médicos retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum médico encontrado")
    })
    @RequestMapping(value = "/get/{especialidade}", method = RequestMethod.GET)
    ResponseEntity<Object> buscarMedicosByEspeciadade(@PathVariable Especialidade especiadade);

    @Operation(summary = "Deleta um médico pelo CPF", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Médico deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    @RequestMapping(value = "/delete/{cpf}", method = RequestMethod.DELETE)
    ResponseEntity<Object> deleteMedicoByCpf(@PathVariable String cpf);

    @Operation(summary = "Atualiza um médico pelo CPF", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    @RequestMapping(value = "/update/{cpf}", method = RequestMethod.PUT)
    ResponseEntity<Object> updateMedicoById(@PathVariable String cpf, @RequestBody CreateMedicoDTO request);
}