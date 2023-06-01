package org.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private PreparedStatement createSt;
    private PreparedStatement readSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteSt;
    private PreparedStatement printSt;
    private Statement updateIdSt;

    public ClientService(Connection connection) throws SQLException {
        createSt = connection
                .prepareStatement("INSERT INTO client (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

        readSt = connection
                .prepareStatement("SELECT id, name FROM client WHERE id = ?");

        updateSt = connection
                .prepareStatement("UPDATE client SET name = ? WHERE id = ?");

        deleteSt = connection
                .prepareStatement("DELETE FROM client WHERE id = ?");

        printSt = connection
                .prepareStatement("SELECT id, name FROM client");
    }

    public long create(String name) throws SQLException {

        if (name.length() < 3 || name.length() > 30) {
            throw new IllegalArgumentException("Некоректна довжина імені клієнта");
        }
        long clientId = 0;
        createSt.setString(1, name);
        int createRes = createSt.executeUpdate();

        if (createRes > 0) {
            try (ResultSet generatedKeys = createSt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    clientId = generatedKeys.getLong(1);
                    System.out.println("Клієнт був успішно створений. Ідентифікатор: " + clientId);
                }
            }
        } else {
            System.out.println("Не вдалося створити клієнта.");
        }
        return clientId;
    }

    String getById(long id) throws SQLException{
        String clientName = "";
        readSt.setLong(1, id);
        ResultSet resultSet = readSt.executeQuery();

        if (resultSet.next()) {
            clientName = resultSet.getString("name");
            System.out.println("Клієнт був успішно прочитаний. Ім'я клієнта: " + clientName);
        } else {
            System.out.println("Не вдалося прочитати клієнта.");
        }
        return clientName;
    }

    void setName(long id, String name) throws SQLException {
        updateSt.setString(1, name);
        updateSt.setLong(2, id);
        int updateRes = updateSt.executeUpdate();

        if (updateRes > 0) {
            System.out.println("Клієнт був успішно оновлен.");
        } else {
            System.out.println("Не вдалося оновити клієнта.");
        }
    }

    void deleteById(long id) throws SQLException{
        deleteSt.setLong(1, id);
        int deleteRes = deleteSt.executeUpdate();
        if (deleteRes > 0) {
            System.out.println("Клієнт був успішно видалений.");
        } else {
            System.out.println("Не вдалося знайти клієнта для видалення.");
        }
    }

    List<Client> listAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        ResultSet resultSet = printSt.executeQuery();

        while (resultSet.next()) {
            long clientId = resultSet.getLong("id");
            String clientName = resultSet.getString("name");
            Client client = new Client(clientId, clientName);
            clients.add(client);
        }
        return clients;
    }
}