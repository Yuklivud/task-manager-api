# 📝 Task Manager API

**Task Manager API** is a RESTful backend service for managing tasks, built with **Spring Boot 3.5** and **Java 21**.  
It provides secure JWT-based authentication, role-based access control, and API documentation via **Swagger/OpenAPI**.

## 🚀 Features

- User registration and authentication using JWT
- Role-based access control
- CRUD operations for tasks
- API documentation with Swagger UI
- PostgreSQL integration for persistent storage
- DTO mapping with MapStruct
- Data validation with Hibernate Validator

## 🛠 Tech Stack

- **Java 21**
- **Spring Boot 3.5**
    - Spring Web
    - Spring Security
    - Spring Data JPA
    - Spring AOP
- **PostgreSQL**
- **JWT (jjwt 0.11.5)**
- **MapStruct 1.5.5.Final**
- **Hibernate**
- **SLF4J**
- **Swagger / Springdoc OpenAPI**
- **Maven**
- **JUnit & Spring Security Test**

## 📦 Installation & Running

### 1. Clone the repository
```bash
git clone https://github.com/Yuklivud/task-manager-api.git
cd task-manager-api
````

### 2. Configure the database

Create a PostgreSQL database and update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Run the application

```bash
mvn spring-boot:run
```

or

```bash
mvn clean package
java -jar target/todo-api-0.0.1-SNAPSHOT.jar
```

## 📄 API Documentation

After starting the application, visit:

```
http://localhost:8080/swagger-ui/index.html
```

## 🔑 Authentication

This API uses JWT:

1. Sign up or log in to obtain a token.
2. Pass the token in the `Authorization` header:

```
Authorization: Bearer <your_token>
```

## 🧪 Testing

Run all tests:

```bash
mvn test
```

## 📂 Project Structure

```
src/
└── main/
    ├── java/com/ms/todoapi
    ├── aop         # Cross-cutting concerns (logging)
    ├── config      # Spring configuration classes
    ├── controller  # REST controllers (API endpoints)
    ├── dto         # Data Transfer Objects
    ├── exception   # Custom exceptions and handlers
    ├── mapper      # MapStruct mappers for entity ↔ DTO conversion
    ├── model       # JPA entities
    ├── repository  # Spring Data JPA repositories
    ├── security    # JWT authentication & Spring Security config
    ├── service     # Business logic services
    └── util        # Utility/helper classes
```

## 📜 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
