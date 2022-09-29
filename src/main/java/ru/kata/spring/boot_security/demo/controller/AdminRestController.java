package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {
    private final UserServiceImpl userService;

    private final RoleServiceImpl roleService;

    @Autowired
    public AdminRestController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.index(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername(Principal principal){
        return new ResponseEntity<>(userService.showUserByUsername(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.showUser(id), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user){
        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
