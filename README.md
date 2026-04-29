# Bank Account Management System (Java Swing)

A desktop-based **Bank Account Management System** built in Java using Swing. The application demonstrates **constructor overloading** by creating Savings and Current accounts with different initialization parameters and displaying the account details in a graphical user interface.

## Problem Statement

Develop a Java-based Bank Account Management System that demonstrates constructor overloading by creating different types of accounts, such as Savings and Current, with varying initialization parameters like account number, account holder name, initial balance, and minimum balance, and display the account details after creation.

## Project Objective

This project demonstrates object-oriented programming concepts using:
- Classes and constructors
- Constructor overloading
- Inheritance
- Encapsulation
- Method overriding
- UI-driven data entry and display

## Features

- Account creation form inside the UI
- Create Savings Account objects
- Create Current Account objects
- Select constructor option:
  - Account number and holder name
  - Account number, holder name, and initial balance
  - Account number, holder name, initial balance, and minimum balance
- Validate required input fields
- Prevent duplicate account numbers
- Account table showing created accounts
- Detail panel showing selected account information
- Delete selected account option
- Clear form option

## Tech Stack

- Java (JDK 17+ recommended)
- Java Swing (`javax.swing`)
- AWT (`java.awt`)

## Project Structure

```text
java-bank-account-system/
+-- src/
|   +-- bank/
|       +-- BankAccountManagementSystem.java
+-- out/   (generated after compilation)
```

## Core Classes

All classes are currently included in one file: `BankAccountManagementSystem.java`.

- `BankAccountManagementSystem`:
  - Main JFrame UI
  - Form handling and event wiring
  - Table refresh and validation

- `BankAccount`:
  - Base account class
  - Stores common account details
  - Provides overloaded constructors
  - Returns account details for display

- `SavingsAccount`:
  - Represents a savings account
  - Extends `BankAccount`
  - Demonstrates constructor overloading

- `CurrentAccount`:
  - Represents a current account
  - Extends `BankAccount`
  - Demonstrates constructor overloading

## Constructor Overloading Rules

1. A constructor with only account number and account holder name creates an account with default balance and minimum balance.
2. A constructor with account number, account holder name, and initial balance creates an account with a custom balance.
3. A constructor with account number, account holder name, initial balance, and minimum balance creates an account with full account details.
4. Savings and Current account classes reuse base account initialization and show account-specific type information.

## How to Compile and Run

Run these commands from project root:

```powershell
javac -d out src\bank\BankAccountManagementSystem.java
java -cp out bank.BankAccountManagementSystem
```

## How to Use

1. Launch the application.
2. Select the account type: **Savings Account** or **Current Account**.
3. Enter account number and account holder name.
4. Choose the constructor option.
5. Enter initial balance and minimum balance when required by the selected constructor.
6. Click **Create Account**.
7. View the created account in the account table.
8. Select an account from the table to view its full details.
9. To delete an account, select it from the table and click **Delete Selected Account**.

## Notes

- The project is focused on constructor overloading.
- No database or file storage is used.
- Account details are stored in memory while the application is running.
- Duplicate account numbers are blocked.

## Possible Enhancements

- Add deposit and withdrawal methods
- Add balance validation based on minimum balance
- Persist account records to file or database
- Separate classes into different files/packages
- Add search/filter on the account table
- Export account details as CSV or PDF

---

Developed as a Java Swing OOP project for demonstrating constructor overloading in a bank account management system.
