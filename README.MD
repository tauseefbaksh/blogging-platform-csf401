# Blogging Platform - Spring Boot Project 9

**Student Name:** Tauseef Baksh 
**Student ID:** 12320526 
**Project Number:** 9 - Blogging Platform  
**Course:** CSF401 - CA2 Assignment  
**Submission Date:** December 24, 2025

## Project Description

A comprehensive RESTful blogging platform built with Spring Boot that enables authors to create and manage blog posts, profiles, comments, and tags. The system implements all mandatory requirements including full CRUD operations, custom queries with JPA relationships, Spring profiles for different environments, and Spring Boot Actuator for monitoring.

## Technologies Used

- **Java:** 17
- **Spring Boot:** 3.2.0
- **Spring Data JPA:** For database operations
- **MySQL:** Production database
- **H2:** Development database
- **Lombok:** Reduces boilerplate code
- **Maven:** Build tool

## Entity Relationships

1. **Author ↔ Profile:** OneToOne (bidirectional)
2. **Post → Author:** ManyToOne
3. **Post ↔ Comment:** OneToMany
4. **Post ↔ Tag:** ManyToMany

## Features Implemented

✅ Full CRUD operations for all 5 entities  
✅ 10+ custom queries with JPA relationships  
✅ Spring Profiles (dev with H2, prod with MySQL)  
✅ Spring Boot Actuator endpoints  
✅ Input validation  
✅ Clean architecture (Controller → Service → Repository)  
✅ Lombok for cleaner code  
✅ @Transactional for data integrity

## Custom Queries (10+)

1. Find posts by tag name
2. Find comments on a specific post
3. Find posts by author ID
4. Find most commented posts
5. Find posts in a date range
6. Find tags used by an author
7. Find recent posts (top 10)
8. Search posts by keyword in title
9. Find authors with most posts
10. Find untagged posts

## Setup Instructions

### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8.0+ (for prod profile)
- IntelliJ IDEA

### Running in Development Mode (H2)
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
Access H2 Console: http://localhost:8080/h2-console

### Running in Production Mode (MySQL)
1. Install and start MySQL
2. Update `application-prod.yml` with your MySQL password
3. Run:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## API Endpoints

### Authors
- GET `/api/authors` - Get all authors
- GET `/api/authors/{id}` - Get author by ID
- POST `/api/authors` - Create author
- PUT `/api/authors/{id}` - Update author
- DELETE `/api/authors/{id}` - Delete author
- GET `/api/authors/most-posts` - Authors with most posts

### Posts
- GET `/api/posts` - Get all posts
- GET `/api/posts/{id}` - Get post by ID
- POST `/api/posts` - Create post
- PUT `/api/posts/{id}` - Update post
- DELETE `/api/posts/{id}` - Delete post
- GET `/api/posts/tag/{tagName}` - Posts by tag
- GET `/api/posts/author/{authorId}` - Posts by author
- GET `/api/posts/most-commented` - Most commented posts
- GET `/api/posts/recent` - Recent posts
- GET `/api/posts/search?keyword={keyword}` - Search posts
- GET `/api/posts/untagged` - Untagged posts
- GET `/api/posts/date-range?start={}&end={}` - Posts in range

### Comments
- GET `/api/comments` - Get all comments
- GET `/api/comments/{id}` - Get comment by ID
- POST `/api/comments` - Create comment
- PUT `/api/comments/{id}` - Update comment
- DELETE `/api/comments/{id}` - Delete comment
- GET `/api/comments/post/{postId}` - Comments by post

### Tags
- GET `/api/tags` - Get all tags
- GET `/api/tags/{id}` - Get tag by ID
- POST `/api/tags` - Create tag
- PUT `/api/tags/{id}` - Update tag
- DELETE `/api/tags/{id}` - Delete tag

### Profiles
- GET `/api/profiles` - Get all profiles
- GET `/api/profiles/{id}` - Get profile by ID
- GET `/api/profiles/author/{authorId}` - Profile by author
- POST `/api/profiles` - Create profile
- PUT `/api/profiles/{id}` - Update profile
- DELETE `/api/profiles/{id}` - Delete profile

## Actuator Endpoints

- `/actuator/health` - Health status
- `/actuator/info` - Application info
- `/actuator/metrics` - Application metrics
- `/actuator/env` - Environment properties

## Testing

Use Postman or curl to test endpoints. Sample requests are in the API documentation.

## GitHub Repository

https://github.com/tauseefbaksh/blogging-platform-csf401.git

## License

Academic project for CSF401 CA2 Assignment.