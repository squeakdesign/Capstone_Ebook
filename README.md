Ebook Website
This Spring Boot application provides an eBook store platform where users can browse, view, and purchase eBooks. Administrators have full control over managing books and categories, while regular users can browse books, add them to their carts, and make purchases. It includes a home page displaying various available books with details, a login page for user authentication, and a registration page for new users.

Features

User Authentication: Registration and login functionality for users.
Admin Control: Manage books and categories (CRUD operations).
User Interface: Responsive design using HTML, CSS, Thymeleaf, and Bootstrap.
Backend: Java, Spring Boot, MySQL for data storage.
Validation: JavaScript for form validation.
Home Page: Displays available books with details such as title, author, and summary.
Login Page: Allows existing users to log in by entering their email and password.
Registration Page: Allows new users to register by providing necessary details.


Technologies Used

Backend: Java, Spring Boot, MySQL
Frontend: HTML, CSS, Thymeleaf, Bootstrap, JavaScript


Project Structure

Models:
UserDetails: User information.
Product: eBook details.
Category: Categories for organizing eBooks.

Controllers:
HomeController: Handles user interactions and cart management.
AdminController: Manages product and category CRUD operations.
UserController: User-specific functionalities.
Repositories: Data access layers for interacting with the database.

Services: Business logic and service layers.

Usage
Admin Functions:

Access /admin to manage books and categories.
Add new books and categories using the provided forms.
User Functions:

Navigate to / to view available eBooks.
Click on a book to view details and add it to the cart.
Access /cart to view and manage items in the cart.
Proceed to checkout to complete the purchase.


Contributing
Contributions are welcome! If you'd like to contribute to this project, feel free to fork the repository and submit a pull request.

License
This project is licensed under the MIT License.
