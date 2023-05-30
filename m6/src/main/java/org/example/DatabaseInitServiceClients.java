package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitServiceClients {
    public static void main(String[] args) {
        String filePath = "src/main/sql/init_db_clients.sql";
        try {
            Connection connection = Database.getConnection();

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            PreparedStatement preparedStatement = null;

            while (line != null) {
                stringBuilder.append(line);
                if (line.endsWith(";")) {
                    String query = stringBuilder.toString();
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.execute();
                    stringBuilder.setLength(0);
                }
                line = reader.readLine();
            }

            reader.close();
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            connection.close();

            System.out.println("Базу даних успішно проініціалізовано!");
        } catch (SQLException e) {
            System.err.println("Помилка SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }
}