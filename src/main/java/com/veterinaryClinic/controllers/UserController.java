package com.veterinaryClinic.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaryClinic.services.UserServices;
import com.veterinaryClinic.models.LoginRequest;
import com.veterinaryClinic.models.User;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/vc")
public class UserController {

    @Autowired
    private UserServices userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticateUser(@RequestBody LoginRequest loginRequest) {
    try {
        User user = userService.authenticateUser(loginRequest);

        if (user != null) {
            String userType = user.getUserType(); 

            Map<String, Object> response = new HashMap<>();
            response.put("userType", userType);

            if ("root".equals(userType)) {
                return ResponseEntity.ok(response);
            }
            else if ("user".equals(userType)) {
                response.put("identificationNumber", user.getIdentificationNumber());
                response.put("username", user.getUsername());
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Unknown user type");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Authentication failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    } catch (Exception e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", "Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

}
