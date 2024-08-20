package com.veterinaryClinic.repositories;

import java.util.Optional;
import com.veterinaryClinic.models.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername_ShouldReturnUser_WhenUsernameExists() {
        
        // Execution
        Optional<User> foundUser = userRepository.findByUsername("Isabel");

        // Assertion
        assertTrue(foundUser.isPresent());
        assertEquals("Isabel", foundUser.get().getUsername());
        assertEquals("user", foundUser.get().getUserType());
        assertEquals(1, foundUser.get().getIdentificationNumber());
    }

    @Test
    void findByUsername_ShouldReturnEmpty_WhenUsernameDoesNotExist() {
        Optional<User> foundUser = userRepository.findByUsername("NonExistingUser");

        assertFalse(foundUser.isPresent());
    }

    @Test
    void findByUsername_ShouldReturnroot_WhenUsernameExists() {
        
        // Execution
        Optional<User> foundUser = userRepository.findByUsername("Margarita");

        // Assertion
        assertTrue(foundUser.isPresent());
        assertEquals("Margarita", foundUser.get().getUsername());
        assertEquals("root", foundUser.get().getUserType());
        assertEquals(0, foundUser.get().getIdentificationNumber());
    }

    
}
