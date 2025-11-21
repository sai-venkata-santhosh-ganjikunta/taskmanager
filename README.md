# Task Manager API  
A simple and secure Task Manager REST API built using **Java**, **Spring Boot**, **Spring Security (JWT)**, and **PostgreSQL**.  
This project implements user authentication, role-based access control, and CRUD operations for managing tasks.

---

## 🚀 Features

### 🔐 Authentication & Security
- User registration & login  
- JWT-based authentication  
- Role-based authorization  
- Password hashing with BCrypt  

### 📝 Task Management
- Create, update, delete, and fetch tasks  
- User-specific task separation  
- Clean REST API architecture  

### 🛠️ Tech Stack
- **Java 17+**  
- **Spring Boot**  
- **Spring Security (JWT)**  
- **Spring Data JPA**  
- **PostgreSQL / H2**  
- **Maven**

---

## ⚙️ Run Locally

### ✔️ 1. Clone the Repository
```
git clone https://github.com/sai-venkata-santhosh-ganjikunta/taskmanager.git
cd taskmanager
```

### ✔️ 2. Configure Database

#### Option A — PostgreSQL
Edit `src/main/resources/application.properties`:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanager
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.security.filter.dispatcher-types=REQUEST
```

#### Option B — H2 (for simple testing)
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=create
spring.h2.console.enabled=true
```

---

## ▶️ 3. Build & Run

Using Maven Wrapper:
```
./mvnw spring-boot:run
```

Or using Maven:
```
mvn spring-boot:run
```

---

## 📖 API Testing

### ✔️ **Swagger UI (most testing done here)**
Run your app, then open:

```
http://localhost:8080/swagger-ui/index.html
```

You can test:
- Registration  
- Login  
- JWT-protected endpoints  
- Task CRUD APIs  

### ✔️ **H2 Console (database inspection)**  
```
http://localhost:8080/h2-console
```

### ✔️ **Health Check (PowerShell)**
You tested this successfully:

```powershell
curl.exe -i "http://localhost:8080/actuator/health"
```

Expected output (I got this):
```
HTTP/1.1 200
```

---

## 🔐 Authentication Flow

1. User registers → password hashed with BCrypt  
2. User logs in → JWT token issued  
3. Client sends token in header:
```
Authorization: Bearer <token>
```
4. Backend validates token  
5. User allowed/denied based on roles  

---

## 📌 API Endpoints

### Auth
| Method | Endpoint        | Description          |
|--------|------------------|----------------------|
| POST   | `/auth/register` | Register user        |
| POST   | `/auth/login`    | Login & get JWT      |

### Tasks (Require JWT)
| Method | Endpoint         | Description           |
|--------|------------------|-----------------------|
| GET    | `/api/tasks`     | Get user tasks        |
| POST   | `/api/tasks`     | Create a task         |
| PUT    | `/api/tasks/{id}`| Update a task         |
| DELETE | `/api/tasks/{id}`| Delete a task         |

---

## 🧪 Sample Requests

### Login
```
curl.exe -X POST "http://localhost:8080/auth/login" `
  -H "Content-Type: application/json" `
  -d "{\"email\":\"test@gmail.com\",\"password\":\"123456\"}"
```

### Authenticated Request
```
curl.exe -X GET "http://localhost:8080/api/tasks" `
  -H "Authorization: Bearer <your-jwt-token>"
```

---

## 📘 Future Improvements
- Add deployment (Render / Railway)  
- Add refresh tokens  
- Add pagination / filtering  
- Add Swagger tags & better documentation  

---

## 👤 Author
**Sai Venkata Santhosh**  
GitHub: https://github.com/sai-venkata-santhosh-ganjikunta/taskmanager 

---

