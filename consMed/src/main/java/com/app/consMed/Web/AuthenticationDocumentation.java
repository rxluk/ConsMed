package com.app.consMed.Web;

import com.app.consMed.Modules.Admin.DTOs.CreateAdminDTO;
import com.app.consMed.Modules.Medico.DTOs.CreateMedicoDTO;
import com.app.consMed.Modules.Recepcionista.DTOs.CreateRecepcionistaDTO;
import com.app.consMed.Modules.User.DTOs.LoginDTO;
import com.app.consMed.Modules.User.DTOs.LoginResponseDTO;
import com.app.consMed.Modules.User.DTOs.CreateUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface AuthenticationDocumentation {

    @Operation(summary = "Realiza o login de um usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha na autenticação")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO json);

    @Operation(summary = "Registra um novo usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha no registro")
    })
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    ResponseEntity<String> register(@RequestBody @Valid CreateUserDTO json);

    @Operation(summary = "Registra um novo administrador", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Administrador registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha no registro")
    })
    @RequestMapping(value = "/admin/register", method = RequestMethod.POST)
    ResponseEntity<Object> registerAdmin(@RequestBody CreateAdminDTO json);

    @Operation(summary = "Registra uma nova recepcionista", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recepcionista registrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha no registro")
    })
    @RequestMapping(value = "/recepcionista/register", method = RequestMethod.POST)
    ResponseEntity<Object> registerRecepcionista(@RequestBody CreateRecepcionistaDTO json);

    @Operation(summary = "Registra um novo médico", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Médico registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha no registro")
    })
    @RequestMapping(value = "/medico/register", method = RequestMethod.POST)
    ResponseEntity<Object> registerMedico(@RequestBody CreateMedicoDTO json);
}