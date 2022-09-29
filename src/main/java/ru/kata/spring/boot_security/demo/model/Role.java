package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name", nullable = false, unique = true)
    private String name;


    @ManyToMany (mappedBy = "roles")
    private List<User> users = new ArrayList<>();


    @Override
    public String getAuthority() {
        return getName();
    }

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        if (name.equals("ROLE_USER"))
            return "USER";
        return "ADMIN";
    }
}
