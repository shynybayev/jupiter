package kz.example.jupiter.registers.impl;

import kz.example.jupiter.application.ImporterAll;
import kz.example.jupiter.database.DBConnect;
import kz.example.jupiter.model.ClientRecord;
import kz.example.jupiter.registers.TestRegister;
import kz.greetgo.util.RND;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ContextConfiguration(classes = ImporterAll.class)
public class TestRegistrImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DBConnect connect;


    private ClientRecord newClientGroup(String clientGroup) throws Exception {
        ClientRecord record = new ClientRecord();
        record.id = RND.str(10);
        record.fio = RND.intStr(10) + " " + RND.str(10) + " " + RND.str(10);

        try (Connection connection = connect.getDataSource().getConnection()) {

            try(PreparedStatement ps = connection.prepareStatement("INSERT INTO client(id, fio, client_group) values (?, ?, ?)"
            )) {
                ps.setString(1, record.id);
                ps.setString(2, record.fio);
                ps.setString(3, clientGroup);

                ps.executeUpdate();
            }
        }
        return record;
    }

    @Autowired
    TestRegister testRegister;

    @Test
    public void clientList() throws Exception {

        String clientGroup = RND.str(10);
        List<ClientRecord> clientRecords = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            clientRecords.add(newClientGroup(clientGroup));
        }

        clientRecords.sort(Comparator.comparing(x -> x.fio));

        List<ClientRecord> actualList = testRegister.getClientList(clientGroup);

        assertThat(actualList).hasSameSizeAs(clientRecords);
        assertThat(actualList.get(1).id).isEqualTo(clientRecords.get(1).id);
        assertThat(actualList.get(1).fio).isEqualTo(clientRecords.get(1).fio);
    }

}