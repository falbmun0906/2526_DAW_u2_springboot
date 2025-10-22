package com.example.springboot.service;

import com.example.springboot.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Servicio que gestiona la persistencia de usuarios en archivo JSON
 */
@Service
public class UserService {
    
    @Value("${app.data.file:data/users.json}")
    private String dataFile;
    
    @Value("${app.data.dir:data}")
    private String dataDir;
    
    private final ObjectMapper objectMapper;
    private final AtomicLong idCounter;
    
    public UserService() {
        this.objectMapper = new ObjectMapper();
        this.idCounter = new AtomicLong(0);
    }
    
    /**
     * Inicializa el directorio de datos y carga los usuarios existentes
     */
    @PostConstruct
    public void init() {
        File dir = new File(dataDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        File file = new File(dataFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
                saveUsers(new ArrayList<>());
            } catch (IOException e) {
                System.err.println("Error al crear el archivo de datos: " + e.getMessage());
            }
        }
        
        // Actualizar el contador con el ID más alto
        List<User> users = getAllUsers();
        if (!users.isEmpty()) {
            long maxId = users.stream()
                    .mapToLong(User::getId)
                    .max()
                    .orElse(0);
            idCounter.set(maxId);
        }
    }
    
    /**
     * Obtiene todos los usuarios
     */
    public List<User> getAllUsers() {
        try {
            File file = new File(dataFile);
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            System.err.println("Error al leer usuarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * Obtiene un usuario por su ID
     */
    public Optional<User> getUserById(Long id) {
        return getAllUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
    
    /**
     * Crea un nuevo usuario
     */
    public User createUser(User user) {
        user.setId(idCounter.incrementAndGet());
        List<User> users = getAllUsers();
        users.add(user);
        saveUsers(users);
        return user;
    }
    
    /**
     * Actualiza un usuario existente
     */
    public Optional<User> updateUser(Long id, User updatedUser) {
        List<User> users = getAllUsers();
        Optional<User> existingUser = users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
        
        if (existingUser.isPresent()) {
            users.removeIf(user -> user.getId().equals(id));
            updatedUser.setId(id);
            users.add(updatedUser);
            saveUsers(users);
            return Optional.of(updatedUser);
        }
        
        return Optional.empty();
    }
    
    /**
     * Elimina un usuario por su ID
     */
    public boolean deleteUser(Long id) {
        List<User> users = getAllUsers();
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        if (removed) {
            saveUsers(users);
        }
        return removed;
    }
    
    /**
     * Guarda la lista de usuarios en el archivo JSON
     */
    private void saveUsers(List<User> users) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(dataFile), users);
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }
}
