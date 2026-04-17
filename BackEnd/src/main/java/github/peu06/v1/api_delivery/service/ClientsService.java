package github.peu06.v1.api_delivery.service;

import github.peu06.v1.api_delivery.model.Clients;
import github.peu06.v1.api_delivery.model.RoleUsers;
import github.peu06.v1.api_delivery.repository.ClientsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientsService {

    private final ClientsRepository repository;

    public ClientsService(ClientsRepository repository) {
        this.repository = repository;
    }

    public Clients create(Clients clients){
        if (repository.existsByEmail(clients.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }

        if (repository.existsByCpf(clients.getCpf())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado");
        }

        clients.setRole(RoleUsers.CLIENT);

        return repository.save(clients);
    }

    public Clients read(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public Clients update(Long id, Clients updateClient){
        Clients clients = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clients.setNome(updateClient.getNome());
        clients.setCpf(updateClient.getCpf());
        clients.setEmail(updateClient.getEmail());
        clients.setTelefone(updateClient.getTelefone());
        clients.setEnderecos(updateClient.getEnderecos());
        if (updateClient.getSenha() != null) {
            clients.setSenha(updateClient.getSenha());
        }
        clients.setRole(RoleUsers.CLIENT);

    return repository.save(clients);
    }

    public void delete (Long id){
        Clients clients = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encotrado"));

        repository.delete(clients);
    }
}
