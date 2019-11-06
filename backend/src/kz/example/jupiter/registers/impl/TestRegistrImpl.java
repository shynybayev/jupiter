package kz.example.jupiter.registers.impl;

import kz.example.jupiter.database.DBConnect;
import kz.example.jupiter.model.ClientRecord;
import kz.example.jupiter.registers.TestRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestRegistrImpl implements TestRegister {
    @Autowired
    DBConnect dbConnect;

    @Override
    public List<ClientRecord> getClientList(String clientGroup) throws Exception {
        try(Connection connection = dbConnect.getDataSource().getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, fio from jupiter.client where client_group = ?  order by fio")) {
                ps.setString(1, clientGroup);

                try(ResultSet rs = ps.executeQuery()) {

                    List<ClientRecord> clientRecords = new ArrayList<>();

                    while (rs.next()) {
                        ClientRecord record = new ClientRecord();
                        record.id = rs.getString("id");
                        record.fio = rs.getString("fio");
                        clientRecords.add(record);
                    }
                    return clientRecords;
                }
            }
        }
    }
}
