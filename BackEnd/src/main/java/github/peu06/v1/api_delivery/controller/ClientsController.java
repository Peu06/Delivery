package github.peu06.v1.api_delivery.controller;

import github.peu06.v1.api_delivery.model.Clients;
import github.peu06.v1.api_delivery.service.ClientsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientsService service;

    public ClientsController(ClientsService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Clients postClients(@RequestBody Clients clients){
        return service.create(clients);
    }

    @GetMapping("/{id}")
    public Clients getClients(@PathVariable Long id){
        return service.read(id);
    }

    @PutMapping("/{id}")
    public Clients putClients(@PathVariable("id") Long id, @RequestBody Clients clients){
        return service.update(id, clients);
    }

    @DeleteMapping("/{id}")
    public void deleteClients(@PathVariable Long id){
        service.delete(id);
    }
}
