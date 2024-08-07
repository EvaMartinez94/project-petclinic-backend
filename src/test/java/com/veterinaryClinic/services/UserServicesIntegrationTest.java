package com.veterinaryClinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.veterinaryClinic.models.LoginRequest;
import com.veterinaryClinic.models.User;
import com.veterinaryClinic.repositories.UserRepository;

@SpringBootTest
@Transactional
class UserServiceIntegrationTest {

    @Autowired
    private UserServices userServices;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    @Test
    void authenticateUser_ShouldReturnUser_WhenCredentialsAreValidForUser() {
        // Execution
        LoginRequest loginRequest = new LoginRequest("Isabel", "1234");
        User authenticatedUser = userServices.authenticateUser(loginRequest);

        // Assertion
        assertNotNull(authenticatedUser);
        assertEquals("Isabel", authenticatedUser.getUsername());
        assertEquals("user", authenticatedUser.getUserType());
        assertEquals(1, authenticatedUser.getIdentificationNumber());
    }
    @Test
    void authenticateUser_ShouldReturnNull_WhenCredentialsAreInvalidForUser() {
        // Execution
        LoginRequest loginRequest = new LoginRequest("Isabel", "wrongPassword");
        User authenticatedUser = userServices.authenticateUser(loginRequest);

        // Assertion
        assertNull(authenticatedUser);
    }

    @Test
    void authenticateUser_ShouldReturnUser_WhenCredentialsAreValidForRoot() {
        // Execution
        LoginRequest loginRequest = new LoginRequest("Margarita", "1234");
        User authenticatedUser = userServices.authenticateUser(loginRequest);

        // Assertion
        assertNotNull(authenticatedUser);
        assertEquals("root", authenticatedUser.getUserType());
    }

    @Test
    void authenticateUser_ShouldReturnNull_WhenCredentialsAreInvalidForRoot() {
        // Execution
        LoginRequest loginRequest = new LoginRequest("Margarita", "wrongPassword");
        User authenticatedUser = userServices.authenticateUser(loginRequest);

        // Assertion
        assertNull(authenticatedUser);
    }
}
