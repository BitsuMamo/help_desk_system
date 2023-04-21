package dao;

import model.Customer;
import model.Ticket;

import java.util.List;
import java.util.Optional;

public class TicketDao implements IDao<Ticket>, IDummyData{
    @Override
    public List<Ticket> getAll() {
        return dm.ticketData.values().stream().toList();
    }

    @Override
    public Optional<Ticket> getById(Integer id) {
        return Optional.ofNullable(dm.ticketData.get(id));
    }

    @Override
    public Ticket create(Ticket data) {
        dm.ticketData.put(data.getId(), data);
        return dm.ticketData.get(data.getId());
    }

    @Override
    public Ticket delete(Integer id) {
        Ticket ticket = dm.ticketData.get(id);
        dm.ticketData.remove(id);
        return ticket;
    }

    public List<Ticket> getByCustomer(Customer customer){
        return dm.ticketData.values().stream()
                .filter((ticket) -> ticket.getCustomer().equals(customer))
                .toList();
    }
}
