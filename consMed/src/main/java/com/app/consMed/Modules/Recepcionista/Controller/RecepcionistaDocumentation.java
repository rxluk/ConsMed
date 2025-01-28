package com.app.consMed.Modules.Recepcionista.Controller;

import com.app.consMed.Modules.Recepcionista.DTOs.CreateRecepcionistaDTO;
import com.app.consMed.Modules.Recepcionista.DTOs.DetailRecepcionistaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface RecepcionistaDocumentation {

    @Operation(summary = "Busca uma recepcionista pelo CPF", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recepcionista encontrada"),
            @ApiResponse(responseCode = "404", description = "Recepcionista não encontrada")
    })
    @RequestMapping(value = "/get/{cpf}", method = RequestMethod.GET)
    ResponseEntity<Object> buscarRecepcionistaByCpf(@PathVariable String cpf);

    @Operation(summary = "Busca todas as recepcionistas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de recepcionistas retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma recepcionista encontrada")
    })
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    ResponseEntity<Object> buscarTodosRecepcionista();

    @Operation(summary = "Deleta uma recepcionista pelo CPF", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Recepcionista deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recepcionista não encontrada")
    })
    @RequestMapping(value = "/delete/{cpf}", method = RequestMethod.DELETE)
    ResponseEntity<Object> deletarAdminByCpf(@PathVariable String cpf);

    @Operation(summary = "Atualiza uma recepcionista pelo CPF", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recepcionista atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Recepcionista não encontrada")
    })
    @RequestMapping(value = "/update/{cpf}", method = RequestMethod.PUT)
    ResponseEntity<Object> atualizarRecepcionistaByCpf(@PathVariable String cpf, @RequestBody CreateRecepcionistaDTO request);
}