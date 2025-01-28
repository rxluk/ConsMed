package com.app.consMed.Modules.Medico.Service;

import com.app.consMed.Modules.Medico.DTOs.CreateMedicoDTO;
import com.app.consMed.Modules.Medico.Domain.Medico;
import com.app.consMed.Modules.Medico.Enums.Especialidade;
import com.app.consMed.Modules.Medico.Repository.MedicoRepository;
import com.app.consMed.Modules.User.DTOs.CreateUserDTO;
import com.app.consMed.Modules.User.Domain.User;
import com.app.consMed.Modules.User.Service.UserService;
import com.app.consMed.Modules.Utils.Contato;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private UserService userService;

    public Optional<Medico> findById(Long id) {
        return medicoRepository.findById(id);
    }

    public Optional<Medico> findByCpf(String cpf) {
        return medicoRepository.findByCpf(cpf);
    }

    public Optional<Medico> findByCrm(String crm) {
        return medicoRepository.findByCrm(crm);
    }

    public List<Medico> findByEspecialidade(Especialidade especialidade) {
        return medicoRepository.findByEspecialidade(especialidade);
    }

    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    public Medico registerMedico(CreateMedicoDTO json) {
        if(medicoRepository.findByCpf(json.cpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado!");
        }

        User user = userService.register(new CreateUserDTO(json.login(), json.password(), json.role()));
        Contato contato = new Contato(json.estado(), json.cidade(), json.bairro(), json.rua(), json.numero(), json.telefone());
        Medico medico = new Medico(json.nome(), json.cpf(), json.crm(), json.especialidade(), contato, user);

        return medicoRepository.save(medico);
    }
    @Transactional
    public Medico updateMedicoByCpf(String cpf, CreateMedicoDTO json) {
        Optional<Medico> medico = findByCpf(cpf);

        if(!medico.isPresent()) {
            throw new IllegalArgumentException("CPF não cadastrado!");
        }

        Optional<User> user = Optional.ofNullable(userService.updateUserByLogin(
                medico.get().getUser().getLogin(),
                new CreateUserDTO(json.login(), json.password(), json.role())
        ));
        if(user.isPresent()) {
            Contato contato = new Contato(json.estado(), json.cidade(), json.bairro(), json.rua(), json.numero(), json.telefone());
            medico.get().setNome(json.nome());
            medico.get().setCpf(json.cpf());
            medico.get().setCrm(json.crm());
            medico.get().setEspecialidade(json.especialidade());
            medico.get().setContato(contato);
            medico.get().setUser(user.get());
            return medicoRepository.save(medico.get());
        }
        throw new RuntimeException("Erro ao atualizar os dados!");
    }

    @Transactional
    public void deleteMedicoByCpf(String cpf) {
        Optional<Medico> medico = medicoRepository.findByCpf(cpf);
        if(!medico.isPresent()) {
            throw new IllegalArgumentException("Recepcionista não encontrado!");
        }
        userService.deleteUserByLogin(medico.get().getUser().getLogin());
        medicoRepository.delete(medico.get());
    }
}
