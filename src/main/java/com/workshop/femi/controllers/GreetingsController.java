package com.workshop.femi.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greetings")
public class GreetingsController {
    @GetMapping
    public ResponseEntity<String> getGreetings() {
        return ResponseEntity.ok("Hello from Femi's Spring Security!");
    }

    @GetMapping("/say-good-bye")
    public ResponseEntity<String> sayGoodBye() {
        return ResponseEntity.ok("Goodbye and see you later from Femi's Spring Security!");
    }
}
