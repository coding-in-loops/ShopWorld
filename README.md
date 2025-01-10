# ShopWorld

**ShopWorld** is an e-commerce application that allows users to browse products, manage their shopping cart, place orders, and much more. It consists of two parts: the **Frontend** and **Backend**. Below are the detailed instructions on how to set up and run the backend and frontend applications locally.

---

## Table of Contents
1. [Project Structure](#project-structure)
2. [Installation](#installation)
3. [Setting Up the Backend](#setting-up-the-backend)
   - [Application Properties](#application-properties)
   - [pom.xml](#pomxml)
4. [Setting Up the Frontend](#setting-up-the-frontend)
5. [Running the Application Locally](#running-the-application-locally)
6. [Technologies Used](#technologies-used)

---

## Project Structure

ShopWorld/
│
├── Backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── shopworld/
│   │   │   │           ├── controller/            # REST API Controllers
│   │   │   │           ├── model/                 # Entity classes
│   │   │   │           ├── repository/            # JPA Repositories
│   │   │   │           ├── service/               # Service classes
│   │   │   │           └── ShopWorldApplication.java  # Main Spring Boot Application Class
│   │   │   ├── resources/
│   │   │   │   ├── application.properties         # Backend Configuration
│   │   │   │   └── static/                        # Static assets (if needed) 
|   |   |   |   |__ template/                      # html files
│   │   │   └── webapp/
│   │   ├── pom.xml                                # Maven configuration file
|
│
├── Frontend/                                      # Frontend Folder (React app)
│   ├── src/                                       # React app source code
│   ├── public/                                    # Public assets (HTML, images, etc.)
│   └── package.json                               # NPM dependencies
└── README.md                                      # Project Documentation


### Explanation of Directories:

- **`src/main/java/com/shopworld`**: Contains Java files for controllers, models, repositories, services, and security configuration.
- **`src/main/resources/static`**: Static files like CSS, JavaScript, and images.
- **`src/main/resources/templates`**: HTML templates rendered by Thymeleaf.
- **`application.properties`**: Configuration file for the backend.
- **`pom.xml`**: Maven dependency management file for the backend.
- **`db.sql`**: SQL script to initialize the database.

## Installation

1. Clone the repository to your local machine:
   git clone https://github.com/coding-in-loops/ShopWorld.git
2. Navigate to the project directory:
    cd ShopWorld
3. Setting Up the Backend

## Application Properties
The application.properties file is located in src/main/resources/. Here's how you should configure it:

# Server Configuration
server.port=8080

# Database Configuration (Use your own DB credentials)
spring.datasource.url=jdbc:mysql://localhost:3306/shopworld
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

# Spring Security Configuration
spring.security.user.name=admin
spring.security.user.password=admin123
spring.security.authorities=ROLE_ADMIN

# Logging Configuration
logging.level.org.springframework=INFO
Make sure to replace the database credentials with your own.

pom.xml

The pom.xml file contains the dependencies and configuration for Maven to build and run the backend application. Below is an example of the essential dependencies included:

<dependencies>
   <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.thymeleaf.extras</groupId>
      <artifactId>thymeleaf-extras-springsecurity6</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
      <scope>runtime</scope>
      <optional>true</optional>
    </dependency>
    <dependency>
      <groupId>com.mysql</groupId>
      <artifactId>mysql-connector-j</artifactId>
      <scope>runtime</scope>
    </dependency>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-test</artifactId>
      <scope>test</scope>
    </dependency>
</dependencies>

## Setting Up the Frontend
The frontend consists of HTML, CSS, and JavaScript files stored in src/main/resources/templates (HTML templates) and src/main/resources/static (CSS, JavaScript, and image files).

HTML Templates: The templates folder contains dynamic pages powered by Thymeleaf, including:

admin/: Pages for the admin panel.
seller/: Pages for the seller panel.
user/: Pages for the user panel.
base.html: Base layout template used across all pages.
CSS & JS: The static folder contains:

css/style.css: The main stylesheet for the application.
js/script.js: JavaScript files for frontend functionality.
img/: Directory for images used in the frontend.

## Running the Application Locally
Backend:
To run the backend, navigate to the project directory and execute the following command:
  mvn spring-boot:run
This will start the Spring Boot server on http://localhost:8080.

Frontend:
The frontend is directly served by Spring Boot and rendered through Thymeleaf templates. Once the backend is running, open your browser and navigate to:
Admin Panel: http://localhost:8080/admin
Seller Panel: http://localhost:8080/seller
User Panel: http://localhost:8080/

## Technologies Used
Backend: Java, Spring Boot, Spring Security, Thymeleaf, JPA (Hibernate), MySQL
Frontend: HTML, CSS, JavaScript, Bootstrap
Security: Spring Security with Role-Based Authentication
Database: MySQL
