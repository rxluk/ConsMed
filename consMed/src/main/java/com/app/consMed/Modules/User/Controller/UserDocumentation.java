package com.app.consMed.Modules.User.Controller;

import com.app.consMed.Modules.User.DTOs.DetailUserDTO;
import com.app.consMed.Modules.User.DTOs.CreateUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface UserDocumentation {

    @Operation(summary = "Busca um usuário pelo login", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @RequestMapping(value = "/get/{login}", method = RequestMethod.GET)
    ResponseEntity<Object> BuscarUserPorLogin(@PathVariable String login);

    @Operation(summary = "Busca todos os usuários", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum usuário encontrado")
    })
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    ResponseEntity<Object> BuscarTodosUsuarios();

    @Operation(summary = "Deleta um usuário pelo login", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @RequestMapping(value = "/delete/{login}", method = RequestMethod.DELETE)
    ResponseEntity<Object> DeletarUsuario(@PathVariable String login);

    @Operation(summary = "Atualiza um usuário pelo login", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @RequestMapping(value = "/put/{login}", method = RequestMethod.PUT)
    ResponseEntity<Object> atualizarUsuario(@PathVariable String login, @RequestBody @Valid CreateUserDTO createUserDTO);

    @Operation(summary = "Busca todos os usuários com senha", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum usuário encontrado")
    })
    @RequestMapping(value = "/get/all-with-password", method = RequestMethod.GET)
    ResponseEntity<Object> BuscarTodosUsuariosWithPassword();
}