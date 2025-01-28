package com.app.consMed.Modules.Admin.Controller;

import com.app.consMed.Modules.Admin.DTOs.CreateAdminDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface AdminDocumentation {

    @Operation(summary = "Busca um administrador pelo CPF", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador encontrado"),
            @ApiResponse(responseCode = "404", description = "Administrador não encontrado")
    })
    @RequestMapping(value = "/get/{cpf}", method = RequestMethod.GET)
    ResponseEntity<Object> buscarAdminPorCpf(@PathVariable String cpf);

    @Operation(summary = "Busca todos os administradores", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de administradores retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum administrador encontrado")
    })
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    ResponseEntity<Object> buscarTodosAdmin();

    @Operation(summary = "Deleta um administrador pelo CPF", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Administrador deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Administrador não encontrado")
    })
    @RequestMapping(value = "/delete/{cpf}", method = RequestMethod.DELETE)
    ResponseEntity<Object> deleteAdmin(@PathVariable String cpf);

    @Operation(summary = "Atualiza um administrador pelo CPF", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Administrador não encontrado")
    })
    @RequestMapping(value = "/update/{cpf}", method = RequestMethod.PUT)
    ResponseEntity<Object> updateAdmin(@PathVariable String cpf, @RequestBody CreateAdminDTO request);
}