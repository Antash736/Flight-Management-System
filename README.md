# ✈️ Flight Booking System

A console-based flight reservation system supporting multiple user roles, built with Object-Oriented Design principles. Designed for customers, travel agents, and administrators to manage flights, bookings, and payments effectively.

---

## 👥 System Users

- **Customers** – Search flights, make bookings, and manage their travel.
- **Travel Agents** – Add/manage flights and assist customers with bookings.
- **System Administrator** – Control user access and system configurations.

---

## 🌟 Core Features

### 1. 🔐 User Authentication & Profile Management
- Secure login/logout with role-based access (Customer, Agent, Admin)
- Profile creation and editing
- Input validation and session handling  
**Security:**  
- Passwords must be ≥6 characters, include letters and numbers  
- User data stored securely in files  

### 2. ✈️ Flight Management
- Add, update, and search flights (origin, destination, date)
- Track seat availability and view schedules  
**Flight Data Includes:**
- Flight number, airline, route, schedule
- Seat classes (Economy, Business, First Class)
- Prices and available seats

### 3. 📦 Booking Management
- Create, modify, or cancel bookings
- Manage passenger info and seat selections
- Generate itineraries and booking references

### 4. 💳 Payment and Ticketing
- Simulated payment processing with multiple methods
- E-ticket generation after successful payment
- Booking status tracking and transaction history

---

## 🔧 Object-Oriented Design

### ✔️ Inheritance
- Base `User` class extended by `Customer`, `Agent`, `Administrator`
- Specialized `Flight` and `Booking` subclasses

### ✔️ Encapsulation
- All class attributes are private
- Controlled access through getters/setters
- File handling encapsulated in `FileManager`

### ✔️ Polymorphism
- Methods like `calculatePrice()` behave differently across classes
- Interface-based design for payments

### ✔️ Abstraction
- Abstract `User` class and interfaces like `PaymentProcessor`
- Simplified access to complex logic (e.g., `calculateFare()`)

### ✔️ Class Relationships
- Composition: `Customer` → `Booking`, `Booking` → `Passenger`
- Aggregation: `Flight` → `Seats`
- Central system coordination via `BookingSystem` class

---

## 💾 Data Storage

- `users.txt` – User credentials and roles  
- `flights.txt` – Flight details  
- `bookings.txt` – Booking data  
- `passengers.txt` – Passenger information  
- Proper formatting and exception handling with try-catch blocks

---

## 🖥️ User Interface

- Console-based menus tailored by user role
- Clear prompts, tables, and error messages
- Optionally: GUI using Java Swing

---

## 🧱 Key Classes Overview

| Class           | Responsibility                                  |
|----------------|--------------------------------------------------|
| `User` (abstract)     | Login, logout, profile management             |
| `Customer`      | Search & book flights, view bookings            |
| `Agent`         | Manage flights/bookings, generate reports       |
| `Administrator` | Manage users and system settings                |
| `Flight`        | Store flight info and check seat availability   |
| `Booking`       | Create and manage booking records               |
| `Passenger`     | Manage passenger data and preferences           |
| `Payment`       | Simulate and validate payment operations        |
| `BookingSystem` | Core logic handler across all system modules    |
| `FileManager`   | Handle file read/write operations               |

---

## 🚀 Additioal Features

- **GUI Version**: Java Swing-based interface for flights and bookings
- **Database Version**: Store data in SQLite instead of text files with secure queries

---

## 📌 Technologies Used

- **Language**: Java  
- **UI**: Console (Optional: Swing GUI)  
- **Storage**: File I/O (Optional: SQLite DB)

