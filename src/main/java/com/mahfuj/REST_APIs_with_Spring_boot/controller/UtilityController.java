package com.mahfuj.REST_APIs_with_Spring_boot.controller;

import com.mahfuj.REST_APIs_with_Spring_boot.service.UtilityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UtilityController {

    @Autowired private UtilityService service;

    // ------------------------------------------------------------
    // 01. AGE CALCULATOR
    // ------------------------------------------------------------
    @GetMapping("/age/{dob}")
    public Object age(@PathVariable String dob) {
        return service.calculateAge(LocalDate.parse(dob));
    }

    // ------------------------------------------------------------
    // 02. BMI CALCULATOR
    // ------------------------------------------------------------
    @GetMapping("/bmi/{weight}/{height}")
    public Map<String, Object> bmi(@PathVariable double weight, @PathVariable double height) {
        // Service now returns a Map with category info
        return service.calculateBMI(weight, height);
    }

    // ------------------------------------------------------------
    // 03. EMI CALCULATOR
    // ------------------------------------------------------------
    @GetMapping("/emi/{amount}/{rate}/{years}")
    public Map<String, Object> emi(
            @PathVariable double amount,
            @PathVariable double rate,
            @PathVariable int years
    ) {
        return service.calculateEMI(amount, rate, years);
    }

    // ------------------------------------------------------------
    // 04. CELSIUS TO FAHRENHEIT
    // ------------------------------------------------------------
    @GetMapping("/convert/celsius/{c}")
    public Map<String, Object> celsiusToF(@PathVariable double c) {
        return service.celsiusToFahrenheit(c);
    }
    // ------------------------------------------------------------
    // 05. PASSWORD STRENGTH CHECKER
    // ------------------------------------------------------------
    @GetMapping("/password/{text}")
    public Map<String, Object> password(@PathVariable String text) {
        return service.passwordStrength(text);
    }

    // ------------------------------------------------------------
    // 06. FIBONACCI SEQUENCE GENERATOR
    // ------------------------------------------------------------
    @GetMapping("/fibonacci/{n}")
    public Map<String, Object> fibonacci(@PathVariable int n) {
        return service.generate(n);
    }

    // ------------------------------------------------------------
    // 07. PALINDROME CHECKER
    // ------------------------------------------------------------
    @GetMapping("/palindrome/{value}")
    public Map<String, Object> palindrome(@PathVariable String value) {
        return service.check(value);
    }

    // ------------------------------------------------------------
    // 08. PRIME NUMBER CHECKER
    // ------------------------------------------------------------
    @GetMapping("/prime/{number}")
    public Map<String, Object> checkPrime(@PathVariable int number) {
        return service.checkPrime(number);
    }

    // ------------------------------------------------------------
    // 09. NUMBER TO WORD CONVERTER
    // ------------------------------------------------------------
    @GetMapping("/words/{number}")
    public Map<String, Object> convertNumberToWords(@PathVariable long number) {
        return service.convertNumberToWords(number);
    }

    // ------------------------------------------------------------
    // 10. GET DETAILED DATE AND TIME
    // ------------------------------------------------------------
    @GetMapping("/datetime")
    public Map<String, Object> getDateTimeDetailed() {
        return service.getDateTimeDetailed();
    }

    // ------------------------------------------------------------
    // 11. IN-MEMORY USER CRUD (Create, Read, Update, Delete)
    // ------------------------------------------------------------

    // POST Request: To create data
    // URL: http://localhost:8080/api/users
    @PostMapping("/users")
    public String createUser(@RequestBody Map<String, Object> body) {
        return service.addUser(body);
    }

    // GET Request: To read all data
    // URL: http://localhost:8080/api/users
    @GetMapping("/users")
    public Object getUsers() {
        return service.getAllUsers();
    }

    // GET Request: Get a single user by ID (Index)
    // URL: http://localhost:8080/api/users/{index}
    @GetMapping("/users/{index}")
    public Object getUser(@PathVariable int index) {
        Map<String, Object> user = service.getUser(index);

        if (user != null) {
            return user;
        } else {
            // Return a simple error message if not found
            return Map.of("error", "User not found with ID: " + index);
        }
    }

    // PUT Request: To update data
    // URL: http://localhost:8080/api/users/{index}
    @PutMapping("/users/{index}")
    public String updateUser(@PathVariable int index, @RequestBody Map<String, Object> body) {
        return service.updateUser(index, body);
    }

    // DELETE Request: To delete data
    // URL: http://localhost:8080/api/users/{index}
    @DeleteMapping("/users/{index}")
    public String deleteUser(@PathVariable int index) {
        return service.deleteUser(index);
    }

}


