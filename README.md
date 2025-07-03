📚 KnowledgeBase App

A full-stack knowledge management system built with Spring Boot (Java) and ReactJS, supporting role-based access, JWT authentication, pagination, search filters, and CRUD operations for articles and categories.

🧩 Project Structure

knowledgebase-project/
├── backend/     --> Spring Boot REST API
│   └── src/main/java/com/kba/...
├── frontend/    --> ReactJS (with Axios + MUI)
│   └── src/components/
├── .gitignore
├── README.md

🛠️ Technologies Used

Backend

Java 21

Spring Boot 3.2+

Spring Security (JWT-based authentication)

Spring Data JPA (Hibernate)

PostgreSQL (or MySQL supported)

Swagger/OpenAPI

Frontend

React 18+

Axios (API calls)

React Router DOM

Material UI (MUI)

🔐 Features

✅ Authentication & Roles

JWT Token login system

Roles: ADMIN, EDITOR, VIEWER

Role-based access control for UI and API

📝 Articles

Create, update, delete (ADMIN/EDITOR only)

Paginated list

Search by title

Filter by category or author

🧭 UI/UX

Login/logout

Protected routes

Material UI styling

Dynamic navbar (based on role)

⚙️ How to Run

📦 Backend

cd backend
./mvnw spring-boot:run

App runs at: http://localhost:8087

💻 Frontend

cd frontend
npm install
npm start

App runs at: http://localhost:3000

📂 API Endpoints

AuthController

POST /api/auth/login → Get JWT token

ArticleController

GET /api/articles?page=0&size=5&sort=title,asc

GET /api/articles/search?query=keyword

GET /api/articles/filter?category=DevOps&author=Satya

POST /api/articles (Admin/Editor)

PUT /api/articles/{id} (Admin/Editor)

DELETE /api/articles/{id} (Admin/Editor)

CategoryController

GET /api/categories

POST /api/categories (Admin)

🪵 Errors & Fix Log (with Timestamps)

Time

Issue

Fix

2025-07-01

403 Forbidden on login

Fixed route mismatch: used /api/auth/login in frontend

2025-07-01

CORS error from React

Added CORS config to Spring Security + WebMvcConfigurer

2025-07-01

jwtDecode import failed

Changed to import jwt_decode from 'jwt-decode'

2025-07-02

JWT secret key too short for HS512

Used Keys.secretKeyFor(SignatureAlgorithm.HS512) for safe key

2025-07-02

Frontend showing blank articles page

Added pagination metadata and token in Axios headers

2025-07-03

Navbar not hiding buttons for VIEWER role

Used getRole() and conditional UI rendering

2025-07-03

Missing edit/delete buttons for articles

Implemented role-based UI + buttons + React Router links

