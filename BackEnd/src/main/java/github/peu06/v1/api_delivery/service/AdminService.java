package github.peu06.v1.api_delivery.service;

import github.peu06.v1.api_delivery.model.Admin;
import github.peu06.v1.api_delivery.model.RoleUsers;
import github.peu06.v1.api_delivery.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository repository;

    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    public Admin create(Admin admin){
        admin.setRole(RoleUsers.ADMIN);
        return repository.save(admin);
    }

    public Admin read(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin não encontrado."));
    }

    public Admin update(Long id, Admin updateAdmin){
        Admin admin = repository.findById(id).orElseThrow(() -> new RuntimeException("Admin não encontrado"));

        admin.setNome(updateAdmin.getNome());
        admin.setEmail(updateAdmin.getEmail());
        if (updateAdmin.getSenha() != null) {
            admin.setSenha(updateAdmin.getSenha());
        }
        admin.setRole(RoleUsers.ADMIN);

        return repository.save(admin);
    }
    
    public void delete (Long id){
        Admin admin = repository.findById(id).orElseThrow(() -> new RuntimeException("Admin não encontrado"));

        if (repository.count() == 1){
            throw new RuntimeException("Não é possível deletar o único admin");
        }

        repository.delete(admin);
    }
}
