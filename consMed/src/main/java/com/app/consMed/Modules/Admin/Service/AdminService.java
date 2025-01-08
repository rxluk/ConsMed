package com.app.consMed.Modules.Admin.Service;

import com.app.consMed.Modules.Admin.DTOs.CreateAdminDTO;
import com.app.consMed.Modules.Admin.Domain.Admin;
import com.app.consMed.Modules.Admin.Repository.AdminRepository;
import com.app.consMed.Modules.User.DTOs.CreateUserDTO;
import com.app.consMed.Modules.User.Domain.User;
import com.app.consMed.Modules.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserService userService;

    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }

    public Optional<Admin> findByCpf(String cpf) {
        return adminRepository.findByCpf(cpf);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin registerAdmin(CreateAdminDTO json) {
        System.out.println("Iniciando o registro de um Admin...");

        if (adminRepository.findByCpf(json.cpf()).isPresent()) {
            System.out.println("Erro: CPF já cadastrado - " + json.cpf());
            throw new IllegalArgumentException("CPF já cadastrado!");
        }

        System.out.println("Criando usuário para o Admin...");
        User user = userService.register(new CreateUserDTO(json.login(), json.password(), json.role()));
        System.out.println("Usuário criado com sucesso: " + user.getLogin());

        System.out.println("Criando instância do Admin...");
        Admin admin = new Admin(json.nome(), json.cpf(), user);

        System.out.println("Salvando Admin no banco de dados...");
        Admin savedAdmin = adminRepository.save(admin);
        System.out.println("Admin salvo com sucesso: ID = " + savedAdmin.getId());

        return savedAdmin;
    }

    @Transactional
    public Admin updateAdminByCpf(String cpf, CreateAdminDTO json) {
        Optional<Admin> admin = findByCpf(cpf);

        if(!admin.isPresent()) {
            throw new IllegalArgumentException("CPF não cadastrado!");
        }
        Optional<User> user = Optional.ofNullable(userService.updateUserByLogin(
                admin.get().getUser().getLogin(),
                new CreateUserDTO(json.login(), json.password(), json.role())
        ));
        if(user.isPresent()) {
            admin.get().setNome(json.nome());
            admin.get().setCpf(json.cpf());
            admin.get().setUser(user.get());
            return adminRepository.save(admin.get());
        }
        throw new RuntimeException("Erro ao atualizar os dados!");
    }

    @Transactional
    public void deleteAdminByCpf(String cpf) {
        Optional<Admin> admin = adminRepository.findByCpf(cpf);
        if(!admin.isPresent()) {
            throw new IllegalArgumentException("Administrador não encontrado!");
        }
        userService.deleteUserByLogin(admin.get().getUser().getLogin());
        adminRepository.delete(admin.get());
    }
}
