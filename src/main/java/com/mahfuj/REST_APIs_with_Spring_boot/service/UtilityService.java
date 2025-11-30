package com.mahfuj.REST_APIs_with_Spring_boot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class UtilityService {


    // ------------------------------------------------------------
    // 01. AGE CALCULATOR
    // ------------------------------------------------------------

    public Map<String, Object> calculateAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();

        // Calculate the difference
        Period period = Period.between(dob, currentDate);

        // Create a readable string for the age
        String ageString = String.format("%d years, %d months, and %d days",
                period.getYears(),
                period.getMonths(),
                period.getDays());

        // Use LinkedHashMap to preserve the order of your specific keys
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("your date of birth", dob.toString());
        response.put("your current age", ageString);

        return response;
    }

    // ------------------------------------------------------------
    // 02. BMI CALCULATOR
    // ------------------------------------------------------------

    public Map<String, Object> calculateBMI(double weight, double height) {
        double bmi = weight / (height * height);
        String category;

        if (bmi < 18.5) category = "Underweight";
        else if (bmi < 24.9) category = "Normal Weight";
        else if (bmi < 29.9) category = "Overweight";
        else category = "Obese";

        return Map.of(
                "inputWeight", weight,
                "inputHeight", height,
                "bmiScore", Math.round(bmi * 100.0) / 100.0, // Limit decimals
                "category", category
        );
    }

    // ------------------------------------------------------------
    // 03. EMI CALCULATOR
    // ------------------------------------------------------------
    public Map<String, Object> calculateEMI(double amount, double rate, int years) {
        double monthlyRate = rate / 12 / 100;
        int months = years * 12;
        double emi = (amount * monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);

        double totalPayment = emi * months;
        double totalInterest = totalPayment - amount;

        return Map.of(
                "monthlyEMI", Math.round(emi * 100.0) / 100.0,
                "totalAmountPaid", Math.round(totalPayment * 100.0) / 100.0,
                "totalInterestPaid", Math.round(totalInterest * 100.0) / 100.0,
                "loanTermMonths", months
        );
    }

    // ------------------------------------------------------------
    // 04. CELSIUS TO FAHRENHEIT
    // ------------------------------------------------------------
    public Map<String, Object> celsiusToFahrenheit(double celsius) {
        double fahrenheit = (celsius * 9/5) + 32;
        return Map.of(
                "celsius", celsius,
                "fahrenheit", fahrenheit,
                "formula", "(°C × 9/5) + 32 = °F"
        );
    }

    // ------------------------------------------------------------
    // 05. PASSWORD STRENGTH CHECKER
    // ------------------------------------------------------------

    public Map<String, Object> passwordStrength(String password) {
        int score = 0;
        List<String> suggestions = new ArrayList<>();

        // Check 1: Length
        if (password.length() >= 8) {
            score++;
        } else {
            suggestions.add("Password is too short (minimum 8 characters required)");
        }

        // Check 2: Uppercase
        if (password.matches(".*[A-Z].*")) {
            score++;
        } else {
            suggestions.add("Add at least one uppercase letter (A-Z)");
        }

        // Check 3: Lowercase
        if (password.matches(".*[a-z].*")) {
            score++;
        } else {
            suggestions.add("Add at least one lowercase letter (a-z)");
        }

        // Check 4: Digits
        if (password.matches(".*\\d.*")) {
            score++;
        } else {
            suggestions.add("Add at least one number (0-9)");
        }

        // Check 5: Special Characters
        if (password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            score++;
        } else {
            suggestions.add("Add at least one special character (e.g., ! @ # $)");
        }

        // Determine Text Label
        String strength;
        if (score <= 2) strength = "Weak";
        else if (score <= 4) strength = "Moderate";
        else strength = "Strong";

        // Build Response
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("password_length", password.length());
        response.put("score", score + "/5");
        response.put("strength", strength);

        if (!suggestions.isEmpty()) {
            response.put("improvements_needed", suggestions);
        } else {
            response.put("message", "Great password!");
        }

        return response;
    }

    // ------------------------------------------------------------
    // 06. FIBONACCI SEQUENCE GENERATOR
    // ------------------------------------------------------------

    public Map<String, Object> generate(int n) {
        if (n <= 0) return Map.of("error", "n must be > 0");

        List<Integer> seq = new ArrayList<>();
        int a = 0, b = 1;

        for (int i = 0; i < n; i++) {
            seq.add(a);
            int temp = a + b;
            a = b;
            b = temp;
        }
        return Map.of("sequence", seq);
    }

    // ------------------------------------------------------------
    // 07. PALINDROME CHECKER
    // ------------------------------------------------------------

    public Map<String, Object> check(String value) {
        String cleaned = value.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();

        return Map.of(
                "value", value,
                "isPalindrome", cleaned.equals(reversed),
                "processed", cleaned,
                "reverse", reversed,
                "length", cleaned.length()
        );
    }

    // ------------------------------------------------------------
    // 08. PRIME NUMBER CHECKER
    // ------------------------------------------------------------

    public Map<String, Object> checkPrime(@PathVariable int number) {
        boolean isPrime = number > 1;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }

        int nextPrime = number + 1;
        while (true) {
            boolean np = true;
            for (int i = 2; i * i <= nextPrime; i++) {
                if (nextPrime % i == 0) {
                    np = false;
                    break;
                }
            }
            if (np) break;
            nextPrime++;
        }

        return Map.of(
                "nextPrime", nextPrime,
                "isPrime", isPrime,
                "number", number

        );
    }

    // ------------------------------------------------------------
    // 09. NUMBER TO WORD CONVERTER
    // ------------------------------------------------------------

    public Map<String, Object> convertNumberToWords(long number) {
        // 1. Convert to words (using a helper method below)
        String words = convert(number);

        // 2. Format as currency (e.g., 1,500)
        String formattedNumber = NumberFormat.getInstance(Locale.US).format(number);

        // 3. Build the User-Friendly Response
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("input_number", number);
        response.put("formatted", formattedNumber);
        response.put("in_words", words + " Only"); // specific format often used in banking

        return response;
    }

    // --- Helper Logic for Number Conversion ---
    private static final String[] units = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] tens = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private String convert(long n) {
        // 0. Handle Negative Numbers
        if (n < 0) return "Minus " + convert(-n);

        // 1. Units (0 - 19)
        if (n < 20) return units[(int) n];

        // 2. Tens (20 - 99)
        if (n < 100) return tens[(int) n / 10] + ((n % 10 != 0) ? " " : "") + units[(int) n % 10];

        // 3. Hundreds (100 - 999)
        if (n < 1000) return units[(int) n / 100] + " Hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);

        // 4. Thousands (1,000 - 999,999)
        if (n < 1000000) return convert(n / 1000) + " Thousand" + ((n % 1000 != 0) ? " " : "") + convert(n % 1000);

        // 5. Millions (1,000,000 - 999,999,999)
        if (n < 1000000000) return convert(n / 1000000) + " Million" + ((n % 1000000 != 0) ? " " : "") + convert(n % 1000000);

        // 6. Billions (1,000,000,000 - 999,999,999,999)
        // Note the 'L' at the end of the number to treat it as a Long
        if (n < 1000000000000L) return convert(n / 1000000000) + " Billion" + ((n % 1000000000 != 0) ? " " : "") + convert(n % 1000000000);

        // 7. Trillions (1 Trillion+)
        return convert(n / 1000000000000L) + " Trillion" + ((n % 1000000000000L != 0) ? " " : "") + convert(n % 1000000000000L);
    }

    // ------------------------------------------------------------
    // 10. GET DETAILED DATE AND TIME
    // ------------------------------------------------------------
    public Map<String, Object> getDateTimeDetailed() {
        LocalDateTime now = LocalDateTime.now();

        // Create a structured Map for the response
        Map<String, Object> response = new HashMap<>();

        // 1. Standard ISO Format (Machine readable)
        response.put("iso_timestamp", now.toString());

        // 2. Human Readable Formats
        response.put("date_readable", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        response.put("time_readable", now.format(DateTimeFormatter.ofPattern("hh:mm:ss a")));

        // 3. Breakdown of components (Useful for UI logic)
        response.put("day_of_week", now.getDayOfWeek().toString());
        response.put("day_of_year", now.getDayOfYear());
        response.put("month", now.getMonth().toString());
        response.put("year", now.getYear());

        // 4. Timezone Info
        response.put("timezone", ZoneId.systemDefault().toString());

        return response;
    }

    // ------------------------------------------------------------
    // 11. IN-MEMORY USER MANAGEMENT (Simulating a Database)
    // ------------------------------------------------------------

    // A list to hold data while the application is running
    private List<Map<String, Object>> userList = new ArrayList<>();

    // POST: Create a new user
    public String addUser(Map<String, Object> userData) {
        userList.add(userData);
        return "User added successfully! Total users: " + userList.size();
    }

    // GET: Retrieve all users
    public List<Map<String, Object>> getAllUsers() {
        return userList;
    }

    // GET: Retrieve a single user by Index
    public Map<String, Object> getUser(int index) {
        if (index >= 0 && index < userList.size()) {
            return userList.get(index);
        } else {
            return null; // Return null if the index doesn't exist
        }
    }

    // PUT: Update a user by Index
    public String updateUser(int index, Map<String, Object> updatedData) {
        if (index >= 0 && index < userList.size()) {
            userList.set(index, updatedData);
            return "User at index " + index + " updated successfully.";
        } else {
            return "User not found at index " + index;
        }
    }

    // DELETE: Remove a user by Index
    public String deleteUser(int index) {
        if (index >= 0 && index < userList.size()) {
            userList.remove(index);
            return "User at index " + index + " deleted successfully.";
        } else {
            return "User not found at index " + index;
        }
    }
}

