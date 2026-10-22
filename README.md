# BuyIt – E-Commerce Cart Simulation

A Java desktop e-commerce application developed as an Object-Oriented Programming (OOP) group project. The current application uses Java Swing for its graphical interface and SQLite for data storage.

## Current Application

**The current application entry point is `BuyItApp.java`.**

Running this class launches `database.gui.HomeFrame`, which opens the application's current GUI.

> **Important:** `Main.java` and `MainFrame.java` belong to an earlier version of the project. They are retained as legacy code and are not the entry point for the current application.

## Features

### Customer
- Customer registration and login
- Browse products by category
- View product listings and prices
- Add and remove products from the shopping cart
- Calculate discounts and order totals
- Checkout and place orders

### Administrator
- Admin login
- Manage products
- View customer orders

## Technologies

- Java
- Java Swing
- SQLite
- JDBC (SQLite JDBC driver)
- Git and GitHub
- Visual Studio Code

## OOP Concepts

- Classes and objects
- Encapsulation
- Inheritance
- Abstraction
- Polymorphism, where applicable
- Exception handling

## Project Structure

- `BuyItApp.java` – entry point for the current GUI application
- `database/` – database connection, setup, and data-access classes
- `database/gui/` – customer and administrator GUI screens
- `Product.java` – common product model
- `Clothing.java`, `Footwear.java`, `Gadget.java` – product subclasses
- `Images/` – product images used by the interface
- `lib/` – SQLite JDBC driver
- `Main.java`, `MainFrame.java` – legacy application code from an earlier version

## Requirements

- A compatible JDK
- SQLite JDBC driver included in the classpath
- The project's SQLite database and database setup code

## How to Run

1. Clone or download the repository.
2. Open the project in a Java IDE such as VS Code.
3. Ensure the SQLite JDBC driver in `lib/` is configured on the project's classpath.
4. Check the database setup and connection configuration.
5. Run **`BuyItApp.java`** to launch the current GUI.

The exact terminal command depends on the project's classpath and compilation setup. Use the configured IDE launch settings or compile the required source files with the SQLite JDBC driver available.

## Database

SQLite stores the application's supported customer, product, and order data. Database operations are handled by classes in the `database/` directory.

## Team

Four-member student OOP project team.
