package kz.example.jupiter.registers;

import kz.example.jupiter.model.ClientRecord;

import java.sql.SQLException;
import java.util.List;

public interface TestRegister {
    List<ClientRecord> getClientList(String clientGroup) throws Exception;
}
