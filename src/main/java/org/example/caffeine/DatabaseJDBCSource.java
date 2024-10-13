package org.example.caffeine;

import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;

public class DatabaseJDBCSource {

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private final String connectionString;

    public DatabaseJDBCSource() {
        this.connectionString = "jdbc:h2:/tmp/h2" + System.currentTimeMillis() + ".db";
        populateTable();
    }

    public void populateTable() {

        try (Connection connection = DriverManager.getConnection(connectionString, "sa", "")) {

            try (Statement statement = connection.createStatement()) {
                statement.execute("create table Entities(id int primary key, s1 varchar, s2 varchar)");
            }

            for (int i = 0; i < 10; i++) {
                try (PreparedStatement prep = connection.prepareStatement("insert into Entities values(?, ?, ?)")) {
                    prep.setInt(1, i);
                    prep.setString(2, String.valueOf(ThreadLocalRandom.current().nextLong()));
                    prep.setString(3, String.valueOf(ThreadLocalRandom.current().nextLong()));
                    prep.execute();
                }
            }

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DatabaseEntity findById(int id) {
        try (Connection connection = DriverManager.getConnection(connectionString, "sa", "")) {
            try (PreparedStatement prep = connection.prepareStatement("select s1, s2 from Entities where id = ?")) {
                prep.setInt(1, id);
                ResultSet resultSet = prep.executeQuery();
                if (resultSet.first()) {
                    return new DatabaseEntity(id, resultSet.getString(1), resultSet.getString(2));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
