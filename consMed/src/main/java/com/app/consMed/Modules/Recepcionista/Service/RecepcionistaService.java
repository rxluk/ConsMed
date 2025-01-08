package com.app.consMed.Modules.Recepcionista.Service;

import com.app.consMed.Modules.Recepcionista.DTOs.CreateRecepcionistaDTO;
import com.app.consMed.Modules.Recepcionista.Domain.Recepcionista;
import com.app.consMed.Modules.Recepcionista.Repository.RecepcionistaRepository;
import com.app.consMed.Modules.User.DTOs.CreateUserDTO;
import com.app.consMed.Modules.User.Domain.User;
import com.app.consMed.Modules.User.Service.UserService;
import com.app.consMed.Modules.Utils.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RecepcionistaService {
    @Autowired
    private RecepcionistaRepository recepcionistaRepository;
    @Autowired
    private UserService userService;

    public Optional<Recepcionista> findById(Long id) {
        return recepcionistaRepository.findById(id);
    }

    public Optional<Recepcionista> findByCpf(String cpf) {
        return recepcionistaRepository.findByCpf(cpf);
    }

    public List<Recepcionista> findAll() {
        return recepcionistaRepository.findAll();
    }

    public Recepcionista registerRecepcionista(CreateRecepcionistaDTO json) {
        if(recepcionistaRepository.findByCpf(json.cpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado!");
        }

        User user = userService.register(new CreateUserDTO(json.login(), json.password(), json.role()));
        Contato contato = new Contato(json.estado(), json.cidade(), json.bairro(), json.rua(), json.numero(), json.telefone());
        Recepcionista recepcionista = new Recepcionista(json.nome(), json.cpf(), contato, user);

        return recepcionistaRepository.save(recepcionista);
    }

    @Transactional
    public Recepcionista updateRecepcionistaByCpf(String cpf, CreateRecepcionistaDTO json) {
        Optional<Recepcionista> recepcionista = findByCpf(cpf);

        if(!recepcionista.isPresent()) {
            throw new IllegalArgumentException("CPF não cadastrado!");
        }

        Optional<User> user = Optional.ofNullable(userService.updateUserByLogin(
                recepcionista.get().getUser().getLogin(),
                new CreateUserDTO(json.login(), json.password(), json.role())
        ));
        if(user.isPresent()) {
            Contato contato = new Contato(json.estado(), json.cidade(), json.bairro(), json.rua(), json.numero(), json.telefone());
            recepcionista.get().setNome(json.nome());
            recepcionista.get().setCpf(json.cpf());
            recepcionista.get().setContato(contato);
            recepcionista.get().setUser(user.get());
            return recepcionistaRepository.save(recepcionista.get());
        }
        throw new RuntimeException("Erro ao atualizar os dados!");
    }

    @Transactional
    public void deleteRecepcionistaByCpf(String cpf) {
        Optional<Recepcionista> recepcionista = recepcionistaRepository.findByCpf(cpf);
        if(!recepcionista.isPresent()) {
            throw new IllegalArgumentException("Recepcionista não encontrado!");
        }
        userService.deleteUserByLogin(recepcionista.get().getUser().getLogin());
        recepcionistaRepository.delete(recepcionista.get());
    }
}
