package com.example.calculator.controller;

import com.example.calculator.model.CalculationRequest;
import com.example.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calculator")
@CrossOrigin("*")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;


    // Endpoint pour obtenir les opérations disponibles
    @GetMapping("/operations")
    public List<String> getAvailableOperations() {
        // Récupère toutes les opérations disponibles
        return calculatorService.getAvailableOperations();
    }

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculate(@RequestBody CalculationRequest request) {
        double result = calculatorService.executeOperation(request.getOperation(), request.getNum1(), request.getNum2());
        return ResponseEntity.ok(result);
    }
}
