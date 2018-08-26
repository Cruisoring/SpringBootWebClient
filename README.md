#SpringBootWebClient
===================

This is a sample web application build with Spring Boot Thymeleaf to validate the CRUD operations of the [sample REST API](https://github.com/Cruisoring/SpringBootUserManagement).

## How to use
After launch the app, [the default base URL](http://localhost:8088/users) would show the list of first 50 users that shall include all users in test environment.

The "Delete" button of the concerned user would call the Detete API to remove it.

The "Details" would call the "/users/{id}" to retrieve a single user and display, where it can be updated with the POST operations.

"Create New User" button, at the bottom of the page, could be used to create new user.