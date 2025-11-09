package com.example.expensetracker.service;

import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    // Register a new user
    public void register(User user) {
        repo.save(user);
    }

    // Check if username already exists
    public boolean userExists(String username) {
        return repo.findByUsername(username).isPresent();
    }

    // Validate login and return User object if valid
    public User validate(String username, String password) {
        Optional<User> userOpt = repo.findByUsername(username);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return userOpt.get();
        }
        return null;
    }
}
