package com.example.expensetracker.controller;

import com.example.expensetracker.model.User;
import com.example.expensetracker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.register(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session) {
        User loggedInUser = userService.validate(user.getUsername(), user.getPassword()); // ✅ Fix
        if (loggedInUser != null) {
            session.setAttribute("username", loggedInUser.getUsername());
            session.setAttribute("role", loggedInUser.getRole()); // optional: store role too
            return "Login successful";
        } else {
            return "Invalid credentials";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out successfully";
    }

    @GetMapping("/current-user")
    public String getCurrentUser(HttpSession session) {
        Object username = session.getAttribute("username");
        return username != null ? username.toString() : "No user logged in";
    }
}
