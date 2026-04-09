package github.peu06.v1.api_delivery.controller;

import github.peu06.v1.api_delivery.model.Admin;
import github.peu06.v1.api_delivery.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Admin postAdmin(@RequestBody Admin admin){
        return service.create(admin);
    }

    @GetMapping("/{id}")
    public Admin getAdmin(@PathVariable Long id){
        return service.read(id);
    }


    @PutMapping("/{id}")
    public Admin putAdmin(@PathVariable("id") Long id, @RequestBody Admin admin){
        return service.update(id, admin);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable Long id){
        service.delete(id);
    }
}
