package kz.example.jupiter.controllers;

import kz.example.jupiter.registers.TestRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestRegister testRegister;

    @GetMapping("/index")
    public String getIndex() {
        return testRegister.getText1();
    }

    @GetMapping("/index2")
    public String getIndex2() {
        return testRegister.getText2();
    }
}
