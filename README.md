---

# Equipment Rental System

## Overview
The Equipment Rental System is a Java application designed to manage equipment rentals for clients. It allows users to perform various operations such as querying available equipment, managing transactions, adding new clients and equipment, and more.

## Architecture
The application follows a layered architecture, dividing its components into distinct layers to promote separation of concerns and maintainability. The layers include:

### 1. Presentation Layer
- Responsible for handling user interactions and displaying information to the user.
- Contains the `Gui` class, which provides a text-based menu-driven interface for users to interact with the system.
- Handles user inputs and invokes appropriate methods from the Business Layer.

### 2. Business Layer
- Implements the core business logic and acts as an intermediary between the Presentation Layer and the Data Access Layer.
- Includes classes `QueryProcessor` and `TransactionProcessor`, which encapsulate the operations related to querying and processing transactions, respectively.

### 3. Data Access Layer
- Manages the storage and retrieval of data from external sources such as text files.
- Includes classes such as `Client`, `Equipment`, and `RentedItem`, which handle data access operations for clients, equipment, and rental items, respectively.

![LayeredArchi drawio](https://github.com/Hasnafi/Tp_ALOG/assets/82615501/ca94780e-84e1-49da-bc1f-ab2c3538a404)

## File Structure
- **App**: Contains the main application class (`LocEngin`) responsible for initializing and running the application.
- **BusinessLayer**: Contains classes (`QueryProcessor`, `TransactionProcessor`) implementing business logic.
- **Data**: Contains text files (`clients.TXT`, `equipments.TXT`, `RentedItems.TXT`, `Stock.TXT`) storing data related to clients, equipment, rented items, and stock.
- **DataAccessLayer**: Contains classes (`Client`, `Equipment`, `RentedItem`) responsible for data access operations.
- **PresentationLayer**: Contains the `Gui` class handling user interactions and displaying the menu-driven interface.

## Usage
To run the application, execute the `LocEngin` class located in the `App` package. Follow the on-screen prompts to navigate through the menu options and perform desired operations.

---

Feel free to adjust the content according to the specific features and functionality of your application!
