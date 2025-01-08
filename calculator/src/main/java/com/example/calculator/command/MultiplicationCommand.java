package com.example.calculator.command;

import org.springframework.stereotype.Component;

@Component
public class MultiplicationCommand implements Command {
    @Override
    public double execute(double num1, double num2) {
        return num1 * num2;
    }
}
