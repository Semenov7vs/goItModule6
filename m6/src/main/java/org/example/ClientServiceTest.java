package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceTest {
    public static void main(String[] args) throws SQLException {
        try {
        Connection connection = Database.getConnection();

        ClientService clientService = new ClientService(connection);

          // СREATE
//        clientService.create("Petr");
//        clientService.create("Ivan");
//        clientService.create("Stepan");

          // READ
//        clientService.getById(1);
//        clientService.getById(2);
//        clientService.getById(3);

            //UPDATE
//        clientService.setName(1, "John");
//        clientService.setName(2, "Sam");
//        clientService.setName(3, "Kim");

            //DELETE
//        clientService.deleteById(3);

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