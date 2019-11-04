package kz.example.jupiter.jupiter;

import kz.example.jupiter.controllers.ImporterController;
import kz.example.jupiter.registers.impl.ImporterRegistr;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ImporterController.class, ImporterRegistr.class})
public class JupiterApplication {

	public static void main(String[] args) {
		SpringApplication.run(JupiterApplication.class, args);
	}

}
