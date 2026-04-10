package github.peu06.v1.api_delivery.controller;

import github.peu06.v1.api_delivery.model.Address;
import github.peu06.v1.api_delivery.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address postAddress(@RequestBody Address address){
        return service.create(address);
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Long id){
        return service.read(id);
    }

    @PutMapping("/{id}")
    public Address putAddress(@PathVariable Long id, Address address){
        return service.update(id, address);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable Long id){
        service.delete(id);
    }
}
