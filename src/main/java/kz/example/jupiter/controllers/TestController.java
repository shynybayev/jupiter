package kz.example.jupiter.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/index")
    public String getIndex() {
        return "Hello from TestController";
    }

    @GetMapping("/index2")
    public String getIndex2() {
        return "Hello from TestController 2";
    }
}
