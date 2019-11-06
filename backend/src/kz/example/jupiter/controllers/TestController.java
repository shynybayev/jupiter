package kz.example.jupiter.controllers;

import kz.example.jupiter.model.ClientRecord;
import kz.example.jupiter.registers.TestRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestRegister testRegister;

    @GetMapping("/client-list")
    public List<ClientRecord> getClientList() throws Exception {
        return testRegister.getClientList("top");
    }
}
