package com.example.calculator.service;

import com.example.calculator.command.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CalculatorService {

    private final Map<String, Command> commands;

    @Autowired
    public CalculatorService(AdditionCommand addition, SubtractionCommand subtraction,
                             MultiplicationCommand multiplication, DivisionCommand division) {
        commands = Map.of(
                "+", addition,
                "-", subtraction,
                "*", multiplication,
                "/", division
        );
    }

    public double executeOperation(String operation, double num1, double num2) {
        Command command = commands.get(operation);
        if (command == null) {
            throw new IllegalArgumentException("Operation not supported: " + operation);
        }
        return command.execute(num1, num2);
    }

    // Récupérer les opérations disponibles pour l'interface
    public List<String> getAvailableOperations() {
        return Arrays.asList("+", "-", "*","/");
    }
}
