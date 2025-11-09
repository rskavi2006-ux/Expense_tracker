package com.example.expensetracker.DTO;

public class Expensedto {

    private String type;        // "income" or "expense"
    private String category;
    private String description;
    private double amount;
    private String date; // We'll parse this to LocalDate in the service layer

    // Constructors
    public Expensedto() {}

    public Expensedto(String type, String category, String description, double amount, String date) {
        this.type = type;
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
