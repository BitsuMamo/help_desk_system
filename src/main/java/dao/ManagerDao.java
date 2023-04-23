package dao;

import JDBC.ConnectManager;
import model.Customer;
import model.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class
ManagerDao implements IDao<Manager>{
    private Connection conn;

    public ManagerDao(){
        conn = new ConnectManager().getConnection();
    }
    @Override
    public List<Manager> getAll() {
        return null;
    }

    @Override
    public Optional<Manager> getById(Integer id)
    {
        Manager returnManger = null;

        String query = "SELECT * FROM 'User' WHERE id = ?";

        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if(!rs.next()){
                return Optional.empty();
            }
            returnManger = new Manager(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("userName"),
                    rs.getString("password")
            );
        }catch (SQLException e){
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }

        return Optional.ofNullable(returnManger);
    }

    @Override
    public Manager create(Manager data) {
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
    public Optional<Manager> delete(Integer id) {
        Optional<Manager> manager = getById(id);
        if(manager.isEmpty()){
            return Optional.empty();
        }

        String query = "DELETE FROM User WHERE id = ?";

        try(PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);

            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return manager;
    }
}
