package com.example.springboot.controller;

import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * Controlador Web para la interfaz de usuario
 * Gestiona las vistas HTML con Thymeleaf
 */
@Controller
public class UserWebController {
    
    private final UserService userService;
    
    @Autowired
    public UserWebController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * GET / - Página principal con lista de usuarios
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }
    
    /**
     * GET /users/new - Formulario para crear nuevo usuario
     */
    @GetMapping("/users/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }
    
    /**
     * POST /users - Procesa la creación de un nuevo usuario
     */
    @PostMapping("/users")
    public String createUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        userService.createUser(user);
        redirectAttributes.addFlashAttribute("message", "Usuario creado exitosamente");
        return "redirect:/";
    }
    
    /**
     * GET /users/edit/{id} - Formulario para editar usuario
     */
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user-form";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/";
        }
    }
    
    /**
     * POST /users/{id} - Procesa la actualización de un usuario
     */
    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user, RedirectAttributes redirectAttributes) {
        Optional<User> updated = userService.updateUser(id, user);
        if (updated.isPresent()) {
            redirectAttributes.addFlashAttribute("message", "Usuario actualizado exitosamente");
        } else {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar usuario");
        }
        return "redirect:/";
    }
    
    /**
     * GET /users/delete/{id} - Elimina un usuario
     */
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("message", "Usuario eliminado exitosamente");
        } else {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar usuario");
        }
        return "redirect:/";
    }
}
