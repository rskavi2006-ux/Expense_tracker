package com.example.expensetracker.controller;

import com.example.expensetracker.DTO.Expensedto;
import com.example.expensetracker.service.Expenseservice;
import com.example.expensetracker.model.Expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "http://localhost:5500") // Change if using another port
public class Expensecontroller {

    @Autowired
    private Expenseservice service;

    // ✅ Add Expense
    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expensedto dto) {
        Expense saved = service.save(dto);
        return ResponseEntity.ok(saved);
    }

    // ✅ Get All Expenses
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(service.findAll());
    }

    // ✅ Delete Expense
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Update Expense
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expensedto dto) {
        Expense updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }
}
