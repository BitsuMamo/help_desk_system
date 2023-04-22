package dao;

import JDBC.ConnectManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {
    public static Integer getLastRow(Connection conn) {
        int id;

        try (
                PreparedStatement statement1 = conn.prepareStatement("select last_insert_rowid()")) {
            ResultSet rs = statement1.executeQuery();

            id = rs.getInt(1);

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }

        return id;
    }
}
