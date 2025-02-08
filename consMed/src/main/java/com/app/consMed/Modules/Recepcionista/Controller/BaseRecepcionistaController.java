package com.app.consMed.Modules.Recepcionista.Controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "Bearer Auth")
@RestController
@RequestMapping("/recepcionista")
public abstract class BaseRecepcionistaController {
}
