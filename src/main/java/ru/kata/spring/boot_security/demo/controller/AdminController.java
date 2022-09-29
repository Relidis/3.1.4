package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getUsers(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.showUserByUsername(userDetails.getUsername());
        model.addAttribute("users", userService.index());
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }



    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user, @RequestParam (value = "nameRoles", required = false) String roles)  {
        user.setRoles(roleService.getByName(roles));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.showUser(id));
        return "edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id, @RequestParam (value = "nameRoles", required = false) String roles) {
        user.setRoles(roleService.getByName(roles));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
