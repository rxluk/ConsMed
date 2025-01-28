package com.app.consMed.Modules.Admin.Controller;

import com.app.consMed.Modules.Admin.DTOs.CreateAdminDTO;
import com.app.consMed.Modules.Admin.DTOs.DetailAdminDTO;
import com.app.consMed.Modules.Admin.Domain.Admin;
import com.app.consMed.Modules.Admin.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminController extends BaseAdminController implements AdminDocumentation {
    @Autowired
    private AdminService adminService;

    @GetMapping("/get/{cpf}")
    public ResponseEntity<Object> buscarAdminPorCpf(@PathVariable String cpf) {
        Optional<Admin> admin = adminService.findByCpf(cpf);
        if(admin.isPresent()) {
            DetailAdminDTO json = new DetailAdminDTO(
                    admin.get().getId(),
                    admin.get().getNome(),
                    admin.get().getCpf(),
                    admin.get().getUser().getLogin(),
                    admin.get().getUser().getRole()
            );
            return ResponseEntity.ok(json);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> buscarTodosAdmin() {
        try {
            List<Admin> admins = adminService.findAll();
            List<DetailAdminDTO> response = new ArrayList<>();
            if(!admins.isEmpty()) {
                for(Admin admin : admins) {
                    response.add(
                            new DetailAdminDTO(
                                    admin.getId(),
                                    admin.getNome(),
                                    admin.getCpf(),
                                    admin.getUser().getLogin(),
                                    admin.getUser().getRole()
                            )
                    );
                }
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Transactional
    @DeleteMapping("/delete/{cpf}")
    public ResponseEntity<Object> deleteAdmin(@PathVariable String cpf) {
        if(!adminService.findByCpf(cpf).isPresent()) return ResponseEntity.notFound().build();
        adminService.deleteAdminByCpf(cpf);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/update/{cpf}")
    public ResponseEntity<Object> updateAdmin(@PathVariable String cpf, @RequestBody CreateAdminDTO request) {
        if(!adminService.findByCpf(cpf).isPresent())
            return ResponseEntity.notFound().build();
        Admin updateAdmin = adminService.updateAdminByCpf(cpf, request);
        if(updateAdmin != null) {
            DetailAdminDTO response = new DetailAdminDTO(
                    updateAdmin.getId(), updateAdmin.getNome(), updateAdmin.getCpf(), updateAdmin.getUser().getLogin(), updateAdmin.getUser().getRole()
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }
}
