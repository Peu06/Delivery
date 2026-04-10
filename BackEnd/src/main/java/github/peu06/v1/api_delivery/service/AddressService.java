package github.peu06.v1.api_delivery.service;

import github.peu06.v1.api_delivery.model.Address;
import github.peu06.v1.api_delivery.model.Clients;
import github.peu06.v1.api_delivery.repository.AddressRepository;
import github.peu06.v1.api_delivery.repository.ClientsRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository repository;
    private final ClientsRepository clientsRepository;

    public AddressService(AddressRepository repository, ClientsRepository clientsRepository) {
        this.repository = repository;
        this.clientsRepository = clientsRepository;
    }

    public Address create(Address address){

        Long clientId = address.getClients().getId();

        Clients client = clientsRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        address.setClients(client);

        return repository.save(address);
    }

    public Address read(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    public Address update(Long id, Address updateAddress){

        Address address = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        if(updateAddress.getClients() != null){
            Long clientId = updateAddress.getClients().getId();

            Clients client = clientsRepository.findById(clientId)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            address.setClients(client);
        }

        address.setLagradouro(updateAddress.getLagradouro());
        address.setNumero(updateAddress.getNumero());
        address.setBairro(updateAddress.getBairro());
        address.setCidade(updateAddress.getCidade());
        address.setEstado(updateAddress.getEstado());
        address.setCep(updateAddress.getCep());

        return repository.save(address);
    }

    public void delete(Long id){
        Address address = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        repository.delete(address);
    }
}