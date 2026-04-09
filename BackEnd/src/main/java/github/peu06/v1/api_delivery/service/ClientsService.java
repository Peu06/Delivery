package github.peu06.v1.api_delivery.service;

import github.peu06.v1.api_delivery.model.Clients;
import github.peu06.v1.api_delivery.model.RoleUsers;
import github.peu06.v1.api_delivery.repository.ClientsRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientsService {

    private final ClientsRepository repository;

    public ClientsService(ClientsRepository repository) {
        this.repository = repository;
    }

    public Clients create(Clients clients){
        clients.setRole(RoleUsers.CLIENT);
        return repository.save(clients);
    }

    public Clients read(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public Clients update(Long id, Clients updateAdmin){
        Clients clients = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clients.setNome(updateAdmin.getNome());
        clients.setCpf(updateAdmin.getCpf());
        clients.setEmail(updateAdmin.getEmail());
        clients.setTelefone(updateAdmin.getTelefone());
        clients.setEnderecos(updateAdmin.getEnderecos());
        if (updateAdmin.getSenha() != null) {
            clients.setSenha(updateAdmin.getSenha());
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
