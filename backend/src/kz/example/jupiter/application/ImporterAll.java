package kz.example.jupiter.application;

import kz.example.jupiter.controllers.ImporterController;
import kz.example.jupiter.database.ImporterDB;
import kz.example.jupiter.registers.impl.ImporterRegistr;
import org.springframework.context.annotation.Import;

@Import({ImporterController.class, ImporterRegistr.class, ImporterDB.class})
public class ImporterAll {
}
