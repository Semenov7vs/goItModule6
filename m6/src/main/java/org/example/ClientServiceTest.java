package org.example;

import org.flywaydb.core.Flyway;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceTest {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:h2:file:C:/Users/these/Desktop/DataBase/finish21";
        String user = "sa";
        String password = "1";

        try {
            Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
            flyway.migrate();
            Connection connection = DriverManager.getConnection(url, user, password);

        ClientService clientService = new ClientService(connection);

////          // СREATE
//        clientService.create("Petr");
//        clientService.create("Ivan");
//        clientService.create("Stepan");

////      //     READ
//        clientService.getById(1);
//        clientService.getById(2);
//        clientService.getById(3);

            //UPDATE
//        clientService.setName(1, "John");
//        clientService.setName(2, "Sam");
//        clientService.setName(3, "Kim");

         //   DELETE
//        clientService.deleteById(3);

        // PRINT
//        List <Client> clientListTest = new ArrayList<>();
//        clientListTest = clientService.listAll();
//        for (Client client : clientListTest) {
//            System.out.println(client);
//        }

       connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}