package dao;

import JDBC.ConnectManager;
import model.Customer;
import model.Manager;
import model.Servicer;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AuthenticationDao{


    private final Connection conn;

    public AuthenticationDao(){
        conn = new ConnectManager().getConnection();
    }

    public Optional<User> logIn(String userName, String password){
        Object returnCustomer = null;
        String type;

        String query = "SELECT * FROM 'User' WHERE userName = ? AND password = ?";

        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, userName);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if(!rs.next()){
                return Optional.empty();
            }

            type = rs.getString("type");
            switch (type){
                case "CUSTOMER" -> {
                    returnCustomer = new Customer(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("userName"),
                            rs.getString("password")
                    );
                }
                case "MANAGER" -> {
                    returnCustomer = new Manager(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("userName"),
                            rs.getString("password")
                    );
                }
                case "SERVICER" -> {
                    returnCustomer = new Servicer(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("userName"),
                            rs.getString("password")
                    );
                }
            }


        }catch (SQLException e){
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }

        return Optional.of((User) returnCustomer);

    }
}
