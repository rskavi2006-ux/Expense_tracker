package com.example.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.DTO.Expensedto;  // ✅ corrected import
import com.example.expensetracker.model.Expense;

import java.time.LocalDate;
import java.util.List;

@Service
public class Expenseservice {

    @Autowired
    private ExpenseRepository repo;

    public Expense save(Expensedto dto) {
        Expense e = new Expense();
        e.setType(dto.getType());
        e.setCategory(dto.getCategory());
        e.setDescription(dto.getDescription());
        e.setAmount(dto.getAmount());
        e.setDate(LocalDate.parse(dto.getDate()));
        return repo.save(e);
    }

    public List<Expense> findAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Expense update(Long id, Expensedto dto) {
        Expense e = repo.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        e.setType(dto.getType());
        e.setCategory(dto.getCategory());
        e.setDescription(dto.getDescription());
        e.setAmount(dto.getAmount());
        e.setDate(LocalDate.parse(dto.getDate()));
        return repo.save(e);
    }
}
