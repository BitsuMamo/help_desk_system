package dao;

import JDBC.ConnectManager;
import model.Customer;
import model.Servicer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicerDao implements IDao<Servicer>{

    private final Connection conn;

    public ServicerDao(){
        conn = new ConnectManager().getConnection();
    }

    @Override
    public List<Servicer> getAll() {
        List<Servicer> returnServicers = new ArrayList<>();

        String query = "SELECT * FROM 'User' WHERE type = 'SERVICER'";

        try(PreparedStatement statement = conn.prepareStatement(query)){

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                returnServicers.add(new Servicer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("userName"),
                        rs.getString("password")
                ));
            }
        }catch (SQLException e){
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }

        return returnServicers;
    }

    @Override
    public Optional<Servicer> getById(Integer id) {
        Servicer returnServicer = null;

        String query = "SELECT * FROM 'User' WHERE id = ?";

        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if(!rs.next()){
                return Optional.empty();
            }
            returnServicer = new Servicer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("userName"),
                    rs.getString("password")
            );
        }catch (SQLException e){
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }

        return Optional.ofNullable(returnServicer);
    }

    @Override
    public Servicer create(Servicer data) {
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
    public Servicer delete(Integer id) {
        Servicer servicer = getById(id).orElse(null);
        String query = "DELETE FROM User WHERE id = ?";

        try(PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);

            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return servicer;
    }
}
