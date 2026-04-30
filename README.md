# 📦 Inventory Management System (Java OOP + JDBC)

A desktop-based Inventory Management System built using **Java (OOP concepts)**, **Swing (GUI)**, and **JDBC (MySQL)**.
This project is designed as a collaborative academic project with modular development.

---

## 🚀 Features

* Manage Products, Stock, Suppliers, and Invoices
* Simple and interactive **Swing-based UI**
* Database connectivity using **JDBC (MySQL)**
* Modular structure for team development
* Basic reporting functionality

---

## 🧠 Tech Stack

* Java (OOP)
* Java Swing (GUI)
* MySQL (Database)
* JDBC (Database Connectivity)
* Git & GitHub (Version Control)

---

## 📁 Project Structure

```
src/
 └── com/inventory/
      ├── db/        # Database connection
      ├── model/     # OOP classes (Product, Supplier, etc.)
      ├── ui/        # Swing UI panels
      └── MainApp.java
```

---

## 👥 Team Responsibilities

### 🔹 Member 1 — Core

* DBConnection (JDBC setup)
* MainDashboard (main UI with tabs)
* Application entry point

---

### 🔹 Member 2 — Product Module

* Product.java (model)
* ProductPanel.java (UI)
* Features: Add & view products

---

### 🔹 Member 3 — Stock Module

* StockItem.java (model)
* StockPanel.java (UI)
* Features: Manage stock levels

---

### 🔹 Member 4 — Supplier Module

* Supplier.java (model)
* SupplierPanel.java (UI)
* Features: Add & manage suppliers

---

### 🔹 Member 5 — Invoice Module

* Invoice.java, InvoiceItem.java (models)
* InvoicePanel.java (UI)
* Features: Create invoices using products

> Depends on Product module

---

### 🔹 Member 6 — Reports & Testing

* SalesReportPanel.java
* Basic reports (e.g., total sales)
* Optional JUnit testing

> Depends on all modules

---

## ⚙️ Setup Instructions

### 1. Clone the repository

```bash
git clone https://github.com/aashna-boop/inventory-management-system.git
cd inventory-management-system
```

---

### 2. Set up MySQL Database

* Create a database (e.g., `inventory_db`)
* Run the `schema.sql` file to create tables

---

### 3. Configure Database Connection

Update credentials in `DBConnection.java`:

```java
DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/inventory_db",
    "your_username",
    "your_password"
);
```

---

### 4. Run the Application

* Open project in VS Code / IntelliJ
* Run `MainApp.java`

---

## 🔄 Git Workflow

Before starting work:

```bash
git pull
```

After making changes:

```bash
git add .
git commit -m "your message"
git push
```

---

## ⚠️ Important Guidelines

* Follow the package structure strictly
* Do not modify others' files without discussion
* Keep implementation simple (focus on OOP + functionality)
* Test your module before pushing

---

## 🎯 Project Goal

This project focuses on:

* Applying **Object-Oriented Programming concepts**
* Building a functional **GUI application**
* Integrating **JDBC with MySQL**
* Practicing **team-based development using Git**

---

## 🤝 Contribution

Each team member works on their assigned module and integrates it into the main project.

---

## 📌 Note

This is an academic project aimed at learning software design, not building a production-level system.

---

✨ Happy coding!
