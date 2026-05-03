# 📦 Inventory Management System (OOP Capstone Project)

## 📌 Overview

This project is a **desktop-based Inventory Management System** built using **Java (Swing GUI)** and **MySQL (JDBC connectivity)**. It enables businesses to efficiently manage products, track stock levels, handle suppliers, generate invoices, and analyze sales reports — all within a structured and user-friendly interface.

The system is designed with strong emphasis on **Object-Oriented Programming (OOP)** principles such as **encapsulation, inheritance, abstraction, polymorphism (overriding & overloading)**, and modular design for team-based development.

---

## 👥 Team Members

* Aashna Suman
* Aaditya Rawat
* Aarush Agrawal
* Madhav Tiwari
* Ikshwaaku
* Isha Tiwari

---

## 🧠 OOP Concepts Implemented

* Encapsulation (private fields + getters/setters)
* Inheritance (shared base classes)
* Polymorphism (method overriding & overloading)
* Abstraction (abstract classes & interfaces)
* Constructors
* Access Modifiers
* Modular architecture for scalability

---

## 🏗️ Project Architecture

### 🔹 Core Module (Member 1 — Backbone)

* `DBConnection.java` → Singleton JDBC connection to MySQL
* `MainApp.java` → Application entry point
* `MainDashboard.java` → Tabbed UI integrating all modules
* `schema.sql` → Database schema (6 tables)
* UML & Database Design

✔ Must be completed first for integration

---

### 🔹 Product Module (Member 2)

* `Product.java`, `Category.java` → Data models
* `ProductPanel.java` → UI for product management
* Features:

  * Add product
  * View product list
  * Database integration
* Uses inheritance, `super`, and code reuse

---

### 🔹 Stock Module (Member 3)

* `StockItem.java` → Stock model
* `StockPanel.java` → Update/view stock
* `StockHistoryPanel.java` → Track stock changes
* Features:

  * Low-stock alerts
* Uses method overloading & overriding

---

### 🔹 Supplier Module (Member 4)

* `Supplier.java` → Supplier model
* `SupplierPanel.java` → UI for supplier management
* `SupplierFormDialog.java` → Add/edit popup form
* Uses abstract classes & interfaces

---

### 🔹 Invoice Module (Member 5)

* `Invoice.java`, `InvoiceItem.java` → Billing models
* `InvoicePanel.java` → Billing UI
* Features:

  * Add items to cart
  * Dynamic total calculation
  * MySQL integration
* Depends on Product module

---

### 🔹 Reports & Testing Module (Member 6)

* `ReportService.java` → Generates:

  * Sales reports
  * Low stock alerts
  * Top products
* `SalesReportPanel.java` → UI for reports
* `AppTest.java` → JUnit testing
* Uses Collections & Generics

---

## 🗄️ Database Schema

Database: `inventory_db`

Tables:

* `products`
* `stock`
* `suppliers`
* `invoice`
* `invoice_items`
* `users` (optional for future authentication)

---

## ⚙️ Setup Instructions

### 1️⃣ Database Setup

```sql
CREATE DATABASE inventory_db;
USE inventory_db;
```

Run the provided `schema.sql` to create all tables.

---

### 2️⃣ MySQL Configuration

* Open MySQL Workbench
* Connect to `MySQL80`
* Password: `root123`

---

### 3️⃣ Compile & Run Application

#### Compile:

```bash
javac -source 8 -target 8 -cp ".;lib/*" -d out src/com/inventory/MainApp.java src/com/inventory/ui/MainDashboard.java src/com/inventory/db/DBConnection.java
```

#### Run:

```bash
java -cp ".;out;lib/*" com.inventory.MainApp
```

---

### 🧪 Compile Test Module

```bash
javac -cp ".;lib/*;out" -d out src/com/inventory/test/AppTest.java
```

---

## 🔄 Git Workflow

### Initial Setup

```bash
git clone https://github.com/aashna-boop/inventory-management-system.git
cd inventory-management-system
git pull origin main
git checkout -b <branch-name>
git push -u origin <branch-name>
```

### Daily Workflow

```bash
git pull origin main
git add .
git commit -m "your message"
git push
```

---

## 💡 Key Highlights

* Clean modular structure for team collaboration
* Strong application of OOP principles
* Real-world business use case
* Scalable and extendable architecture

---

## 📬 Contribution Note

Each member works on their own module branch and integrates via `MainDashboard`. Ensure your module is tested independently before merging.

---

✨ *Built as an academic capstone to demonstrate real-world application of Java OOP, GUI design, and database integration.*
