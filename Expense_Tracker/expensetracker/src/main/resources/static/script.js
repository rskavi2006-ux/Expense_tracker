document.addEventListener("DOMContentLoaded", () => {
    // ---------------- LOGIN ----------------
    const loginForm = document.getElementById("loginForm");
    if (loginForm) {
        loginForm.addEventListener("submit", async (e) => {
            e.preventDefault();
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            const response = await fetch("/api/login", {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({username, password}),
            });

            if (response.ok) {
                window.location.href = "/dashboard";
            } else {
                alert("Login failed. Please check your credentials.");
            }
        });
    }

    // ---------------- REGISTER ----------------
    const registerForm = document.getElementById("registerForm");
    if (registerForm) {
        registerForm.addEventListener("submit", async (e) => {
            e.preventDefault();
            const username = document.getElementById("regUsername").value;
            const password = document.getElementById("regPassword").value;
            const email = document.getElementById("regEmail").value;

            const response = await fetch("/api/register", {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({username, password, email}),
            });

            if (response.ok) {
                alert("Registration successful. Please log in.");
                window.location.href = "/login.html";
            } else {
                alert("Registration failed. Try again.");
            }
        });
    }

    // ---------------- ADD EXPENSE ----------------
    const expenseForm = document.getElementById("expenseForm");
    if (expenseForm) {
        expenseForm.addEventListener("submit", async (e) => {
            e.preventDefault();
            const amount = parseFloat(document.getElementById("amount").value);
            const category = document.getElementById("category").value;
            const date = document.getElementById("date").value;
            const description = document.getElementById("description").value;
            const type = "expense"; // Default type

            const response = await fetch("/api/expenses", {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({amount, category, date, description, type}),
            });

            if (response.ok) {
                alert("Expense added successfully!");
                expenseForm.reset();
                loadExpenses(); // 🔄 Refresh the dashboard after adding
            } else {
                alert("Failed to add expense.");
            }
        });
    }

    // ---------------- LOAD EXPENSES (Dashboard) ----------------
    async function loadExpenses() {
        const expenseList = document.getElementById("expenseList");
        const totalExpenseElem = document.getElementById("totalExpense");

        if (!expenseList) return; // Only runs on dashboard page

        try {
            const response = await fetch("/api/expenses");
            if (!response.ok) throw new Error("Failed to fetch expenses");
            const expenses = await response.json();

            // Render the list
            expenseList.innerHTML = "";
            let total = 0;
            expenses.forEach((exp) => {
                total += exp.amount;
                const li = document.createElement("li");
                li.className = "flex justify-between border-b py-2";
                li.innerHTML = `
          <span>${exp.category} (${exp.description || "No description"})</span>
          <span>₹${exp.amount.toFixed(2)}</span>
        `;
                expenseList.appendChild(li);
            });

            // Update total expense
            if (totalExpenseElem)
                totalExpenseElem.textContent = `₹${total.toLocaleString("en-IN")}`;
        } catch (err) {
            console.error("Error loading expenses:", err);
        }
    }

    // ---------------- LOGOUT ----------------
    const logoutBtn = document.getElementById("logoutBtn");
    if (logoutBtn) {
        logoutBtn.addEventListener("click", async () => {
            await fetch("/api/logout", {method: "POST"});
            window.location.href = "/login";
        });
    }

    // ---------------- INITIAL LOAD ----------------
    loadExpenses();
});
