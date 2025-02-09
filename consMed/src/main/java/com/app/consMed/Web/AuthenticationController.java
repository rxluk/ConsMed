package com.app.consMed.Web;

import com.app.consMed.Modules.Admin.DTOs.CreateAdminDTO;
import com.app.consMed.Modules.Admin.Service.AdminService;
import com.app.consMed.Modules.Medico.DTOs.CreateMedicoDTO;
import com.app.consMed.Modules.Medico.Service.MedicoService;
import com.app.consMed.Modules.Recepcionista.DTOs.CreateRecepcionistaDTO;
import com.app.consMed.Modules.Recepcionista.Service.RecepcionistaService;
import com.app.consMed.Modules.User.DTOs.LoginDTO;
import com.app.consMed.Modules.User.DTOs.LoginResponseDTO;
import com.app.consMed.Modules.User.DTOs.CreateUserDTO;
import com.app.consMed.Modules.User.Service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController implements AuthenticationDocumentation {


    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RecepcionistaService recepcionistaService;
    @Autowired
    private MedicoService medicoService;

    @SecurityRequirement(name = "Bearer Auth")
    @GetMapping("/role")
    public ResponseEntity<String> getUserRole(@RequestHeader("Authorization") String token) {
        try {
            // Remove "Bearer " do token caso esteja presente
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            // Decodifica o token
            DecodedJWT decodedJWT = JWT.decode(token);

            // Extrai a role do payload
            String role = decodedJWT.getClaim("role").asString();

            if (role == null) {
                return ResponseEntity.badRequest().body("Role não encontrada no token!");
            }

            return ResponseEntity.ok(role);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao processar token: " + e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO json) {
        try {
            LoginResponseDTO response = userService.login(json.login(), json.password());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new LoginResponseDTO("Falha na autenticação!"));
        }
    }

    @SecurityRequirement(name = "Bearer Auth")
    @PostMapping("/user/register")
    public ResponseEntity<String> register(@RequestBody @Valid CreateUserDTO json) {
        try {
            userService.register(json);
            return ResponseEntity.ok("Usuário registrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @SecurityRequirement(name = "Bearer Auth")
    @PostMapping("/admin/register")
    public ResponseEntity<Object> registerAdmin(@RequestBody CreateAdminDTO json) {
        try {
            Object registeredAdmin = adminService.registerAdmin(json);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredAdmin);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @SecurityRequirement(name = "Bearer Auth")
    @PostMapping("/recepcionista/register")
    public ResponseEntity<Object> registerRecepcionista(@RequestBody CreateRecepcionistaDTO json) {
        try {
            Object registeredRecepcionista = recepcionistaService.registerRecepcionista(json);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredRecepcionista);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @SecurityRequirement(name = "Bearer Auth")
    @PostMapping("/medico/register")
    public ResponseEntity<Object> registerMedico(@RequestBody CreateMedicoDTO json) {
        try {
            Object registeredMedico = medicoService.registerMedico(json);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredMedico);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}