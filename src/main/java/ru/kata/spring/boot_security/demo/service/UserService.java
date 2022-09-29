package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> index();

    User showUser(Long id);

    User showUserByUsername(String username);

    boolean saveUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    UserDetails loadUserByUsername(String username);
}
