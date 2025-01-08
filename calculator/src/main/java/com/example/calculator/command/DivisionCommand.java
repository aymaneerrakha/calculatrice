package com.example.calculator.command;

import org.springframework.stereotype.Component;

@Component
public class DivisionCommand implements Command {
    @Override
    public double execute(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return num1 / num2;
    }
}
