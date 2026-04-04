# 📊 Finance Dashboard Backend System

## 🔹 Project Overview

This project is a **Finance Dashboard Backend System** built using Spring Boot.
It enables users to manage financial records, perform analytics, and visualize insights through a connected UI dashboard.

The system supports **role-based access control**, **data validation**, and **interactive analytics** including category-wise and monthly trend analysis.

---

## 🔹 Key Features

* 👤 **User Management**

  * Create users
  * Assign roles (ADMIN, ANALYST, VIEWER)
  * Activate / deactivate users

* 💰 **Financial Records Management**

  * Create, update, delete financial records
  * Store details such as amount, type, category, date, and notes

* 📊 **Dashboard Analytics**

  * Total Income
  * Total Expense
  * Net Balance
  * Category-wise distribution
  * Monthly trends (Income vs Expense)

* 🔍 **Advanced Filtering**

  * Filter by type (INCOME / EXPENSE)
  * Filter by category
  * Filter by date range

* 🔐 **Role-Based Access Control**

  * ADMIN → Full access (CRUD operations)
  * ANALYST → Dashboard access
  * VIEWER → Read-only access

* 🛡️ **Validation & Error Handling**

  * Field-level validation using annotations
  * Global exception handling
  * Structured error responses

* 💾 **Persistent Storage**

  * H2 database (file-based persistence)

* 🌐 **Frontend Integration**

  * Simple HTML dashboard
  * Data visualization using charts

---

## 🔹 System Architecture

```
[ Browser UI (HTML + Chart.js) ]
                ↓
[ Spring Boot Backend (REST APIs) ]
                ↓
[ H2 Database (Persistent Storage) ]
```

The frontend interacts with backend APIs to fetch and visualize financial data dynamically.

---

## 🔹 API Endpoints

### 👤 User APIs

```
POST   /users           → Create a new user
GET    /users           → Retrieve all users
PATCH  /users/{id}      → Update user role or status
```

### 💰 Financial Records APIs

```
POST   /records         → Create financial record (ADMIN only)
GET    /records         → Get all records (with optional filters)
PUT    /records/{id}    → Update record (ADMIN only)
DELETE /records/{id}    → Delete record (ADMIN only)
```

### 🔍 Filtering Support

```
GET /records?type=EXPENSE
GET /records?category=Food
GET /records?startDate=2026-04-01&endDate=2026-04-30
GET /records?type=EXPENSE&category=Food&startDate=2026-04-01
```

### 📊 Dashboard APIs

```
GET /dashboard/summary   → Total income, expense, balance
GET /dashboard/category  → Category-wise aggregation
GET /dashboard/trends    → Monthly income vs expense trends
```

---

## 🔹 Sample Request

### Create Financial Record

```json
{
  "amount": 10000,
  "type": "INCOME",
  "category": "Salary",
  "date": "2026-04-03",
  "note": "Monthly salary"
}
```

---

## 🔹 Role Header Usage

All protected APIs require a header:

```
role: ADMIN / ANALYST / VIEWER
```

---

## 🔹 Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* H2 Database
* HTML, CSS, JavaScript
* Chart.js (for visualization)

---

## 🔹 How to Run

1. Clone the repository
2. Run the Spring Boot application
3. Open Postman or browser
4. Access APIs at:

   ```
   http://localhost:9090
   ```
5. Open `index.html` to view the dashboard UI

---

## 🔹 Design Decisions

* Used **layered architecture** (Controller → Service → Repository) for maintainability
* Implemented **role-based access control** using request headers
* Used **H2 database** for lightweight development and persistence
* Designed **aggregation APIs** for dashboard analytics
* Implemented **filtering and trends** to simulate real-world financial systems
* Built a simple UI to demonstrate **data visualization and API integration**

---

## 🔹 Project Highlights

* Clean and modular backend architecture
* Real-world financial analytics implementation
* Interactive dashboard with charts
* Strong validation and error handling
* End-to-end functionality from API to UI

---

## 🔹 Future Enhancements

* JWT-based authentication
* Pagination support
* Export reports (PDF/CSV)
* Deployment on cloud platform

---

## 🔹 Conclusion

This project demonstrates a complete backend system with real-world features such as role-based access, financial analytics, and data visualization.
It reflects strong understanding of backend development, API design, and system architecture.

---
