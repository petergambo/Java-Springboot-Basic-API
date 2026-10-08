# auth-api

Spring Boot API base Application with authentication and validation. Connects to MySQL Database running on local machine. This is a base template for building robust backend APIs with Java and the SpringBoot framework.

## Stack & Dependencies
- Spring Boot 4.1.1
- Java 21
- Spring Web
- Spring Data JPA
- Spring Security
- MariaDB
- Flyway
- JJWT 0.13.0

## Base Endpoints
- POST /api/auth/register
- POST /api/auth/login
- GET /api/users/me

## Database
This source targets XAMPP MariaDB at:
`jdbc:mariadb://localhost:3306/auth_db` 
configure for other databases.

Create the database with:
```sql
CREATE DATABASE auth_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
varies based on database

## Run
If Maven is installed:
`mvn spring-boot:run`

Start app with the windows executable file "run_app_maven.bat" on windows 

