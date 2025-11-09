package com.example.expensetracker.controller;

import com.example.expensetracker.model.User;
import com.example.expensetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:8080") // allow frontend access
public class UserController {

    @Autowired
    private UserService service;

    // 🟢 Register new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (service.userExists(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already taken");
        }

        service.register(user);
        return ResponseEntity.ok("User registered successfully");
    }

    // 🔵 Login existing user
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User loggedInUser = service.validate(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
