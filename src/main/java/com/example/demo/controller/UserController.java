package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service) { this.service = service; }

    @PostMapping("/") public User create(@RequestBody User u) { return service.save(u); }
    @GetMapping("/{id}") public User get(@PathVariable Long id) { return service.findById(id); }
    @GetMapping("/") public List<User> getAll() { return service.findAll(); }
    @PutMapping("/{id}") public User update(@PathVariable Long id, @RequestBody User u) {
        u.setId(id); return service.save(u);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.delete(id); }
}





