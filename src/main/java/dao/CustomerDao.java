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

public class CustomerDao implements IDao<Customer>, IDummyData {
    @Override
    public List<Customer> getAll() {
        List<Customer> returnValue = new ArrayList<>();

        String query = "SELECT * FROM 'User' WHERE type = 'CUSTOMER'";

        try(Connection conn = (new ConnectManager()).getConnection()) {
            PreparedStatement stat = conn.prepareStatement(query);

            try {

                ResultSet rs = stat.executeQuery();
                while(rs.next()){
                    returnValue.add(populateCustomer(rs));
                }
            }catch (SQLException e){
                System.out.println(e.getErrorCode() + " " + e.getMessage());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return returnValue;

    }

    private Customer populateCustomer(ResultSet rs) throws SQLException{
        Integer id = null;
        String name = null;
        String userName = null;
        String password = null;
        if(rs.next()){
            id = rs.getInt("id");
            name = rs.getString("name").trim();
            userName = rs.getString("userName").trim();
            password = rs.getString("password").trim();
        }

        return new Customer(id, name, userName, password);
    }

    @Override
    public Optional<Customer> getById(Integer id) {
        Customer returnValue = null;

        String query = "SELECT * FROM 'User' WHERE id = ?";

        try(Connection conn = (new ConnectManager()).getConnection()) {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, String.valueOf(id));

            try {

                ResultSet rs = stat.executeQuery();
                while(rs.next()){
                    returnValue = populateCustomer(rs);
                }
            }catch (SQLException e){
                System.out.println(e.getErrorCode() + " " + e.getMessage());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return Optional.ofNullable(returnValue);
    }

    @Override
    public Customer create(Customer data) {
        dm.usersData.put(data.getId(), data);
        return (Customer) dm.usersData.get(data.getId());
    }

    @Override
    public Customer delete(Integer id) {
        Customer user = (Customer) dm.usersData.get(id);
        dm.usersData.remove(id);
        return user;
    }
}
