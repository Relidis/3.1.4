package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

public interface RoleService extends UserDetailsService {
    List<Role> getAllRoles();

    List<Role> getByName(String name);


}
