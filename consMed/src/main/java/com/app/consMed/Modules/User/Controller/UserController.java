package com.app.consMed.Modules.User.Controller;

import com.app.consMed.Modules.User.DTOs.DetailUserDTO;
import com.app.consMed.Modules.User.DTOs.CreateUserDTO;
import com.app.consMed.Modules.User.Domain.User;
import com.app.consMed.Modules.User.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController extends BaseUserController implements UserDocumentation {
    @Autowired
    private UserService userService;

    @GetMapping("/get/{login}")
    public ResponseEntity<Object> BuscarUserPorLogin(@PathVariable String login) {
        Optional<User> userDetailsOptional = userService.findByLogin(login);

        if (userDetailsOptional.isPresent()) {
            UserDetails userDetails = userDetailsOptional.get();

            if (userDetails instanceof User) {
                User user = (User) userDetails;
                DetailUserDTO detailUserDTO = new DetailUserDTO(user.getLogin(), user.getRole());
                return ResponseEntity.ok(detailUserDTO);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> BuscarTodosUsuarios() {
        List<User> users = userService.findAll();
        List<DetailUserDTO> allUserDTOs = new ArrayList<>();

        for (User user : users) {
            DetailUserDTO detailUserDTO = new DetailUserDTO(user.getLogin(), user.getRole());
            allUserDTOs.add(detailUserDTO);
        }
        return ResponseEntity.ok(allUserDTOs);
    }

    @DeleteMapping("/delete/{login}")
    public ResponseEntity<Object> DeletarUsuario(@PathVariable String login) {
        userService.deleteUserByLogin(login);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/put/{login}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable String login, @RequestBody @Valid CreateUserDTO createUserDTO) {
        userService.updateUserByLogin(login, createUserDTO);
        return ResponseEntity.ok("Usuario atualizado com sucesso!");
    }

    @GetMapping("/get/all-with-password")
    public ResponseEntity<Object> BuscarTodosUsuariosWithPassword() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
}
