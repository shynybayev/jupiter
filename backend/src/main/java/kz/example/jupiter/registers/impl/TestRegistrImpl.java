package kz.example.jupiter.registers.impl;

import kz.example.jupiter.registers.TestRegister;
import org.springframework.stereotype.Component;

@Component
public class TestRegistrImpl implements TestRegister {

    @Override
    public String getText1() {
        return "Hello from TestRegistrImpl 1";
    }

    @Override
    public String getText2() {
        return "Hello from TestRegistrImpl 2";
    }
}
