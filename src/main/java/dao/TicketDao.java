package dao;

import JDBC.ConnectManager;
import model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements IDao<Ticket>{
    private final Connection conn;
    private final CustomerDao customerDao;
    private final ServicerDao servicerDao;

    public TicketDao(){
        conn = new ConnectManager().getConnection();
        customerDao = new CustomerDao();
        servicerDao = new ServicerDao();
    }
    @Override
    public List<Ticket> getAll() {
        List<Ticket> returnTickets = new ArrayList<>();

        String query = "SELECT * FROM Ticket";
        try(PreparedStatement statement = conn.prepareStatement(query)){
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Integer servicerId = rs.getInt("servicer_id");

                returnTickets.add(new Ticket(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("created_at")),
                        rs.getString("title"),
                        rs.getString("desc"),
                        customerDao.getById(rs.getInt("customer_id")).get(),
                        servicerDao.getById(servicerId).orElse(null),
                        rs.getInt("isFixed") != -1
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return returnTickets;
    }

    @Override
    public Optional<Ticket> getById(Integer id) {
        Ticket returnTicket;
        String query = "SELECT * FROM Ticket WHERE id = ?";
        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if(!rs.next()){
                return Optional.empty();
            }

            Integer servicerId = rs.getInt("servicer_id");

            returnTicket = new Ticket(
                    rs.getInt("id"),
                    LocalDate.parse(rs.getString("created_at")),
                    rs.getString("title"),
                    rs.getString("desc"),
                    customerDao.getById(rs.getInt("customer_id")).get(),
                    servicerDao.getById(servicerId).orElse(null),
                    rs.getInt("isFixed") != -1
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.of(returnTicket);
    }

    @Override
    public Ticket create(Ticket data) {
        String query = "INSERT INTO Ticket (title, created_at, desc, customer_id, isFixed) VALUES (?, ?, ?, ?, ?)";

        int id;

        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, data.getTitle());
            statement.setString(2, data.getCreatedAt().toString());
            statement.setString(3, data.getDescription());
            statement.setInt(4, data.getCustomer().getId());
            statement.setInt(5, data.isFixed() ? 1 : 0);

            statement.executeUpdate();

            id = Util.getLastRow(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return getById(id).orElse(null);
    }

    @Override
    public Optional<Ticket> delete(Integer id) {
        Optional<Ticket> ticket = getById(id);
        if(ticket.isEmpty()){
            return Optional.empty();
        }

        String query = "DELETE FROM Ticket WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ticket;
    }

    public List<Ticket> getTicketByCustomer(Integer customer_id){
        List<Ticket> returnTickets = new ArrayList<>();
        String query = "SELECT * FROM Ticket WHERE customer_id = ?";
        return getTickets(returnTickets, query, customer_id);
    }

    public List<Ticket> getTicketByServicer(Integer servicer_id){
        List<Ticket> returnTickets = new ArrayList<>();

        String query = "SELECT * FROM Ticket WHERE servicer_id = ?";
        return getTickets(returnTickets, query, servicer_id);
    }

    private List<Ticket> getTickets(List<Ticket> returnTickets, String query, Integer id) {
        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()){

                Integer servicerId = rs.getInt("servicer_id");

                returnTickets.add(new Ticket(
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("created_at")),
                        rs.getString("title"),
                        rs.getString("desc"),
                        customerDao.getById(rs.getInt("customer_id")).get(),
                        servicerDao.getById(servicerId).orElse(null),
                        rs.getInt("isFixed") != -1

                ));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return returnTickets;
    }

    public void addServicerToExistingTicket(int ticket_id, Integer servicerId){
        String query = "UPDATE Ticket SET servicer_id = ? WHERE id = ?";

        try(PreparedStatement statement = conn.prepareStatement(query)){
            String servicer = servicerId == null ? null : String.valueOf(servicerId);
            statement.setString(1, servicer);
            statement.setInt(2, ticket_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void resolveTicket(Integer id){
        String query = "UPDATE Ticket SET isFixed = 1 WHERE id = ?";

        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
