package github.peu06.v1.api_delivery.service;

import github.peu06.v1.api_delivery.model.Users;
import github.peu06.v1.api_delivery.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository repository;

    public UsersService(UsersRepository repository) {
        this.repository = repository;
    }

    public Users create(Users users){
        return repository.save(users);
    }

    public Users read(Long id){
        return 
    }
}
