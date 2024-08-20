package com.veterinaryClinic.controllers;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.transaction.annotation.Transactional;

import com.veterinaryClinic.models.User;
import com.veterinaryClinic.repositories.UserRepository;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        String username = "Isabel";
        String password = "1234";
        String rootname = "Margarita";
        String rootpassword = "1234";

        // Verificar si el usuario ya existe
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            // Si no existe, crear un nuevo usuario
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setUserType("user");
            user.setIdentificationNumber(1);
            userRepository.save(user);
        } else {
            // Si ya existe, asegurarse de que la contraseña esté correctamente codificada
            User existingUser = optionalUser.get();
            if (!passwordEncoder.matches(password, existingUser.getPassword())) {
                existingUser.setPassword(passwordEncoder.encode(password));
                userRepository.save(existingUser);
            }
        }

        // Verificar si el usuario root ya existe
        Optional<User> optionalRoot = userRepository.findByUsername(rootname);
        if (optionalRoot.isEmpty()) {
            // Si no existe, crear un nuevo usuario root
            User root = new User();
            root.setUsername(rootname);
            root.setPassword(passwordEncoder.encode(rootpassword));
            root.setUserType("root");
            root.setIdentificationNumber(0);
            userRepository.save(root);
        } else {
            // Si ya existe, asegurarse de que la contraseña esté correctamente codificada
            User existingRoot = optionalRoot.get();
            if (!passwordEncoder.matches(rootpassword, existingRoot.getPassword())) {
                existingRoot.setPassword(passwordEncoder.encode(rootpassword));
                userRepository.save(existingRoot);
            }
        }
    }

    @Test
    void authenticateUser_ShouldReturn200_WhenCredentialsAreValid() throws Exception {
        mockMvc.perform(post("/api/vc/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"Isabel\", \"password\": \"1234\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Isabel"))
                .andExpect(jsonPath("$.userType").value("user"))
                .andExpect(jsonPath("$.identificationNumber").value(1));
    }

    @Test
    void authenticateUser_ShouldReturn401_WhenCredentialsAreInvalid() throws Exception {
        mockMvc.perform(post("/api/vc/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"Isabel\", \"password\": \"wrongPassword\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Authentication failed"));
    }

    @Test
    void authenticateRoot_ShouldReturn200_WhenCredentialsAreValid() throws Exception {
        mockMvc.perform(post("/api/vc/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"Margarita\", \"password\": \"1234\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userType").value("root"));
    }

    @Test
    void authenticateRoot_ShouldReturn401_WhenCredentialsAreInvalid() throws Exception {
        mockMvc.perform(post("/api/vc/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"Margarita\", \"password\": \"wrongPassword\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Authentication failed"));
    }
}
