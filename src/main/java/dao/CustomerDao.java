package dao;

import JDBC.ConnectManager;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDao implements IDao<Customer>{

    private final Connection conn;
    public CustomerDao(){
        conn = new ConnectManager().getConnection();
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> returnCustomers = new ArrayList<>();

        String query = "SELECT * FROM 'User' WHERE type = 'CUSTOMER'";

        try(PreparedStatement statement = conn.prepareStatement(query)){

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                returnCustomers.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("userName"),
                        rs.getString("password")
                ));
            }
        }catch (SQLException e){
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }

        return returnCustomers;
    }

    @Override
    public Optional<Customer> getById(Integer id) {
        Customer returnCustomer = null;

        String query = "SELECT * FROM 'User' WHERE id = ?";

        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if(!rs.next()){
                return Optional.empty();
            }
            returnCustomer = new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("userName"),
                    rs.getString("password")
            );
        }catch (SQLException e){
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }

        return Optional.ofNullable(returnCustomer);
    }

    @Override
    public Customer create(Customer data) {


        String query = "INSERT INTO User (name, userName, password, type) VALUES (?, ?, ?, ?)";

        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, data.getName());
            statement.setString(2, data.getUserName());
            statement.setString(3, data.getPassword());
            statement.setString(4, data.getUserType());

            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return getById(data.getId()).orElse(null);
    }

    @Override
    public Customer delete(Integer id) {
        Customer customer = getById(id).orElse(null);
        String query = "DELETE FROM User WHERE id = ?";

        try(PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);

            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return customer;
    }
}
