# 🛠️ REST APIs with Spring Boot 

Assalamu-alaikum, I am Mahfuj Hasan. Welcome to the **REST APIs with Spring Boot** project! This repository contains a lightweight backend application that provides 10 useful mathematical, logical, and string manipulation tools via RESTful endpoints.

This project demonstrates the creation of **Stateless REST APIs** using the **Spring Boot** framework without the need for a database connection.

## 🚀 Tech Stack
* **Language:** Java 21
* **Framework:** Spring Boot 3.5.8
* **Build Tool:** Maven
* **Tools:** Lombok, Spring Web, Spring Boot DevTools

---
<img src="https://github.com/mahafuj-hasan-91/REST-APIs-with-Spring-boot/blob/main/src/main/resources/templates/localhost_8080_.png?raw=true" alt="description" width="500" height="500">

## 🏃‍♂️ How to Run

1.  Clone the repository:
    ```bash
    git clone https://github.com/mahafuj-hasan-91/REST-APIs-with-Spring-boot.git
    ```
2.  Navigate to the project directory.
3.  Run the application:
    ```bash
    ./mvnw spring-boot:run
    ```
4.  Open your browser or Postman and access the base URL: `http://localhost:8080`

---

## 📚 API Reference & Documentation

Below is the usage guide for all 10 endpoints available in this application.

### 1. Age Calculator
Calculates the exact age (Years, Months, Days) based on the Date of Birth.
* **Method:** `GET`
* **URL:** `/api/age/{dob}`
* **Format:** `YYYY-MM-DD`

**Example:**
`GET /api/age/2000-05-20`

**Response:**
```json
{
  "your date of birth": "2000-05-20",
  "your current age": "25 years, 6 months, and 7 days"
}
```
### 2. BMI Calculator
Calculates Body Mass Index and determines the health category.
* **Method:** `GET`
* **URL:** `/api/bmi/{weight}/{height}`
* **Params:** Weight in KG, Height in Meters.

**Example:**
`GET /api/bmi/75/1.75`

**Response:**
```json
{
  "inputWeight": 75,
  "inputHeight": 1.75,
  "category": "Normal Weight",
  "bmiScore": 24.49
}
```
### 3. EMI Calculator
Calculates the Equated Monthly Installment for loans.
* **Method:** `GET`
* **URL:** `/api/emi/{amount}/{rate}/{years}`

**Example:**
`GET /api/emi/500000/8/20`
*(500,000 Loan, 10% Interest, 5 Years)*

**Response:**
```json
{
  "totalInterestPaid": 503728.08,
  "totalAmountPaid": 1003728.08,
  "loanTermMonths": 240,
  "monthlyEMI": 4182.2
}
```
### 4. Temperature Converter
Converts Celsius to Fahrenheit.
* **Method:** `GET`
* **URL:** `/api/convert/celsius/{c}`

**Example:**
`GET /api/convert/celsius/37.6`

**Response:**
```json
{
  "fahrenheit": 99.68,
  "formula": "(°C × 9/5) + 32 = °F",
  "celsius": 37.6
}
```

### 5. Password Strength Checker
Analyzes a string and determines if it is Weak, Moderate, or Strong.
* **Method:** `GET`
* **URL:** `/api/password/{text}`

**Example:**
`GET /api/password/hello123`

**Response:**
```json
{
  "password_length": 8,
  "score": "3/5",
  "strength": "Moderate",
  "improvements_needed": [
    "Add at least one uppercase letter (A-Z)",
    "Add at least one special character (e.g., ! @ # $)"
  ]
}
```
### 6. Fibonacci Generator
Generates the Fibonacci sequence up to the Nth term.
* **Method:** `GET`
* **URL:** `/api/fibonacci/{n}`

**Example:**
`GET /api/fibonacci/10`

**Response:**
```json
{
  "sequence": [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
}
```
### 7. Palindrome Checker
Checks if a word or sentence reads the same backward as forward.
* **Method:** `GET`
* **URL:** `/api/palindrome/{value}`

**Example:**
`GET /api/palindrome/Madam`

**Response:**
```json
{
  "value": "madam",
  "reverse": "madam",
  "processed": "madam",
  "isPalindrome": true,
  "length": 5
}
```
### 8. Prime Number Checker
Determines if a given integer is a prime number.
* **Method:** `GET`
* **URL:** `/api/prime/{number}`

**Example:**
`GET /api/prime/37`

**Response:**
```json
{
  "isPrime": true,
  "nextPrime": 41,
  "number": 37
}
```
### 9. Number to Words Converter
Converts numeric values into English words.
* **Method:** `GET`
* **URL:** `/api/words/{number}`

**Example:**
`GET /api/words/87329586528`

**Response:**
```json
{
  "input_number": 87329586528,
  "formatted": "87,329,586,528",
  "in_words": "Eighty Seven Billion Three Hundred Twenty Nine Million Five Hundred Eighty Six Thousand Five Hundred Twenty Eight Only"
}
```
### 10. Server Date & Time
Returns the current server date and time details.
* **Method:** `GET`
* **URL:** `/api/datetime`

**Example:**
`GET /api/datetime`

**Response:**
```json
{
  "iso_timestamp": "2025-11-28T00:02:39.936452300",
  "month": "NOVEMBER",
  "day_of_year": 332,
  "year": 2025,
  "timezone": "Asia/Dhaka",
  "time_readable": "12:02:39 AM",
  "date_readable": "2025-11-28",
  "day_of_week": "FRIDAY"
}
```
---
### 👤 Author
**Mahfuj Hasan**
* Project created for learning Spring Boot REST Architecture.

