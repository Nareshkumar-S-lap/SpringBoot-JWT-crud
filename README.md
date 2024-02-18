# Spring Security Configuration for JWT Authentication

This repository contains a sample Spring Security configuration for JWT (JSON Web Token) authentication in a Spring Boot application. It includes setup for user authentication, authorization based on user roles, and token-based authentication using JWT.

## Overview

The Spring Security configuration provided in this repository sets up the following features:

- Authentication using JWT tokens
- Authorization based on user roles (ADMIN, USER)
- Customization for login, registration, and various API endpoints
- Session management to use stateless authentication

## Setup Instructions

To use this configuration in your Spring Boot application, follow these steps:

1. Clone this repository to your local machine:

2. git clone https://github.com/your-username/spring-security-jwt.git

3. Import the project into your preferred IDE (e.g., IntelliJ IDEA, Eclipse).

4. Make sure you have Java and Maven installed on your machine.

5. Customize the configuration according to your application's requirements. You may need to adjust the URL patterns, roles, and authentication mechanisms based on your specific use case.

6. Build the project using Maven:

7. Run the Spring Boot application:


8. Access the application at `http://localhost:8080` (or a different port if configured).

## Usage

Once the application is running, you can use any API testing tool (e.g., Postman) to interact with the endpoints. Here are some common actions:

- **Register a new user**: Send a POST request to `/register` with user details in the request body.
- **Login**: Send a POST request to `/login` with user credentials. You will receive a JWT token in the response.
- **Access protected endpoints**: Include the JWT token in the Authorization header of subsequent requests to access protected endpoints.

## Endpoints

The following endpoints are available:

- `/register`: Register a new user.
- `/login`: Login and obtain a JWT token.
- Various endpoints for managing student data, accessible based on user roles (ADMIN, USER).

For detailed endpoint documentation, refer to the code comments in the `SecurityConfig.java` file.

# Student Enquiry Management API

This Spring Boot application provides endpoints for managing student enquiries, including functionalities for creating, retrieving, updating, and deleting student enquiries, as well as updating the status of student enquiries.

## Endpoints

- **Create Enquiry**: `POST /enquiries/createEnquiries`
- **Retrieve All Enquiries**: `GET /enquiries/getStudents`
- **Retrieve Enquiry by ID**: `GET /enquiries/getStudent/{id}`
- **Update Enquiry**: `PUT /enquiries/updateStudent/{id}`
- **Update Deleted Enquiry**: `PUT /enquiries/updatedeleteStudent/{id}`
- **Delete Enquiry**: `DELETE /enquiries/deleteStudent/{id}`
- **Get Students by Status**: `GET /enquiries/status/{status}`
- **Update Status of Enquiry**: `PUT /enquiries/status/{id}`

## Usage

1. Clone this repository.
2. Import the project into your IDE.
3. Customize the configuration and endpoints as needed.
4. Build and run the project.
5. Access the endpoints using an API testing tool like Postman.

## Contributing

Contributions are welcome! Please feel free to open an issue or submit a pull request if you find any issues or have suggestions for improvements.

## License

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](mvwn) file for details.





