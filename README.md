# 🏥 Healthcare Management System - Microservices Architecture

This is a full-featured **Healthcare Management System** built using **Spring Boot microservices architecture**. The system is designed to manage patients, doctors, appointments, medical records, billing, notifications, and more — all through independently deployable services.

---

## 🧱 Tech Stack

- **Java 17 / Spring Boot**
- **Spring Cloud** (Eureka, Config Server, Gateway)
- **JWT Authentication**
- **MySQL / PostgreSQL**
- **Docker & Docker Compose**
- **RabbitMQ / Kafka** *(optional)*
- **Zipkin + Sleuth** (Distributed Tracing)
- **Swagger/OpenAPI** (API Documentation)

---

## 📦 Microservices Overview

### 🔐 1. Auth Service
Handles user registration, login, JWT token generation, and role-based access control (Admin, Doctor, Patient).

### 🧑 2. Patient Service
Manages patient profiles, contact details, and medical history. Interacts with appointments, medical records, and billing.

### 👨‍⚕️ 3. Doctor Service
Stores doctor information such as specialization, availability, and credentials. Connects to appointment and prescription services.

### 📅 4. Appointment Service
Manages appointment booking, rescheduling, and cancellations between patients and doctors. Validates doctor availability.

### 📁 5. Medical Records Service
Handles patient medical files, lab reports, and historical documents. Secure file uploads and storage.

### 💊 6. Prescription Service
Enables doctors to prescribe medications to patients. Can be linked with a drug inventory or pharmacy service in the future.

### 💰 7. Billing & Insurance Service
Manages patient invoices, billing status, and optional integration with insurance providers and payment gateways.

### 🔔 8. Notification Service
Sends real-time alerts and notifications (SMS, Email) for appointments, prescriptions, test results, and other events.

### 📊 9. Admin & Analytics Service
Provides dashboards and insights to administrators for monitoring system usage, trends, and health metrics.

---

## 🌐 Supporting Infrastructure

### 🚪 API Gateway
Acts as a single entry point for all client requests. Handles routing, authentication, and forwarding to backend services.

### 🧭 Eureka Discovery Server
Registers and discovers all running microservices dynamically for load balancing and fault tolerance.

### ⚙️ Config Server
Centralized configuration management for all microservices using Git or local file storage.

---

## 📌 Project Setup

1. Clone the repository
2. Configure databases and environment variables
3. Start Config Server, Eureka, and Gateway
4. Run each microservice individually or via Docker Compose

---

## 📈 Future Enhancements

- Video consultations
- Patient-doctor chat system
- AI-based symptom checker
- Pharmacy/inventory service
- 2FA / OAuth2 integration

---
